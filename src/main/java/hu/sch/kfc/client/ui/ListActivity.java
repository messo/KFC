package hu.sch.kfc.client.ui;

import java.util.List;
import hu.sch.kfc.client.cache.Cache;
import hu.sch.kfc.client.place.ApplicationPlace;
import hu.sch.kfc.client.place.ShowPlace;
import hu.sch.kfc.client.service.GroupService;
import hu.sch.kfc.client.service.GroupServiceAsync;
import hu.sch.kfc.client.ui.view.ListView;
import hu.sch.kfc.client.ui.view.impl.ListViewImpl;
import hu.sch.kfc.shared.Group;
import com.google.gwt.app.place.PlaceController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Ez az Activity felelős a körök kilistázásáért, tulajdonképpen ez az indító
 * oldal, itt lehet kiválasztani, hogy melyik körtől szeretnénk rendelni.
 *
 * @author  messo
 * @since   0.1
 */
public class ListActivity extends AbstractActivity implements ListView.Listener {

    private static ListView defaultView = null;
    private static List<Group> groups = null;
    private ListView view;
    private GroupServiceAsync groupService = GWT.create(GroupService.class);

    public ListActivity(PlaceController<ApplicationPlace> placeController, ListView view) {
        super(placeController);
        this.view = view;
        view.setListener(this);
    }

    public ListActivity(PlaceController<ApplicationPlace> placeController) {
        this(placeController, getDefaultView());
    }

    private static ListView getDefaultView() {
        if (defaultView == null) {
            defaultView = new ListViewImpl();
        }
        return defaultView;
    }

    @Override
    public void start(Display panel) {
        GWT.log("Activity start: list");
        if (groups == null) {
            groupService.getGroups(new AsyncCallback<List<Group>>() {

                @Override
                public void onSuccess(List<Group> result) {
                    groups = result;
                    for(Group g : result) {
                        Cache.put(g);
                    }
                    view.setGroups(groups);
                }

                @Override
                public void onFailure(Throwable caught) {
                    Window.alert("Hiba a kérésben!");
                }
            });
        } else {
            view.setGroups(groups);
        }
        panel.showActivityWidget(view);
    }

    @Override
    public void groupClicked(Group g) {
        placeController.goTo(new ShowPlace(g.getToken()));
    }

}
