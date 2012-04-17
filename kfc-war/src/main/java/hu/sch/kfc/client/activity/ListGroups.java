package hu.sch.kfc.client.activity;

import hu.sch.kfc.client.place.ShowGroupPlace;
import hu.sch.kfc.client.ui.view.ListGroupsView;
import hu.sch.kfc.shared.proxy.GroupProxy;
import hu.sch.kfc.shared.service.KFCRequestFactory;
import java.util.List;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Ez az Activity felelős a körök kilistázásáért, tulajdonképpen ez az indító oldal, itt lehet
 * kiválasztani, hogy melyik körtől szeretnénk rendelni.
 * 
 * @author messo
 * @since 0.1
 */
public class ListGroups extends AbstractActivity implements ListGroupsView.Listener {

    @Inject
    private ListGroupsView view;
    @Inject
    private KFCRequestFactory requestFactory;

    private static List<GroupProxy> groups = null;

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setListener(this);

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
        placeController.goTo(new ShowGroupPlace(g.getToken()));
    }
}
