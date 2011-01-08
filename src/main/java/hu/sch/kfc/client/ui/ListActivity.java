package hu.sch.kfc.client.ui;

import java.util.List;
import hu.sch.kfc.client.cache.Cache;
import hu.sch.kfc.client.place.ShowPlace;
import hu.sch.kfc.client.service.GroupServiceAsync;
import hu.sch.kfc.client.ui.view.ListView;
import hu.sch.kfc.shared.Group;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * Ez az Activity felelős a körök kilistázásáért, tulajdonképpen ez az indító oldal, itt lehet
 * kiválasztani, hogy melyik körtől szeretnénk rendelni.
 * 
 * @author messo
 * @since 0.1
 */
public class ListActivity extends AbstractActivity implements ListView.Listener {

    @Inject
    private ListView view;
    @Inject
    private GroupServiceAsync groupService;

    private static List<Group> groups = null;

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setListener(this);
        
        GWT.log("Activity start: list");
        if (groups == null) {

            groupService.getGroups(new AsyncCallback<List<Group>>() {

                @Override
                public void onSuccess(List<Group> result) {
                    groups = result;
                    for (Group g : result) {
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
        panel.setWidget(view);
    }

    @Override
    public void groupClicked(Group g) {
        placeController.goTo(new ShowPlace(g.getToken()));
    }
}
