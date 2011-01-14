package hu.sch.kfc.client.ui;

import hu.sch.kfc.client.place.ShowPlace;
import hu.sch.kfc.client.proxy.GroupProxy;
import hu.sch.kfc.client.request.KFCRequestFactory;
import hu.sch.kfc.client.ui.view.ListView;
import java.util.List;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
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
    private KFCRequestFactory requestFactory;

    private static List<GroupProxy> groups = null;

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setListener(this);

        GWT.log("Activity start: list");
        if (groups == null) {

            requestFactory.groupRequest().findGroups().fire(new Receiver<List<GroupProxy>>() {
                @Override
                public void onSuccess(List<GroupProxy> response) {
                    groups = response;
                    view.setGroups(response);
                }
            });
        } else {
            view.setGroups(groups);
        }
        panel.setWidget(view);
    }

    @Override
    public void groupClicked(GroupProxy g) {
        placeController.goTo(new ShowPlace(g.getToken()));
    }
}
