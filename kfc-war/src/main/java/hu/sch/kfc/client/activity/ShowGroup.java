package hu.sch.kfc.client.activity;

import hu.sch.kfc.client.place.EditProgramPlace;
import hu.sch.kfc.client.place.ShowGroupPlace;
import hu.sch.kfc.client.place.ShowProgramPlace;
import hu.sch.kfc.client.ui.view.ShowGroupView;
import hu.sch.kfc.shared.proxy.GroupProxy;
import hu.sch.kfc.shared.proxy.ProgramProxy;
import hu.sch.kfc.shared.service.KFCRequestFactory;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Egy adott körhöz tartozó rendezvényeket listázza ki.
 * 
 * @author messo
 * @since 0.1
 */
public class ShowGroup extends AbstractActivity implements ShowGroupView.Listener {

    @Inject
    private ShowGroupView view;
    @Inject
    private KFCRequestFactory requestFactory;

    private GroupProxy group;
    private String groupToken;

    public Activity withPlace(ShowGroupPlace place) {
        this.groupToken = place.getGroupToken();
        return this;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setListener(this);
        view.reset();

        requestFactory.groupRequest().findGroupByToken(groupToken)
                .with("programs", "programs.orderInterval").fire(new Receiver<GroupProxy>() {
                    @Override
                    public void onSuccess(GroupProxy response) {
                        setGroup(response);
                    }
                });

        panel.setWidget(view);
    }

    protected void setGroup(GroupProxy g) {
        group = g;
        if (group != null) {
            view.setText(g.getName());
            view.setPrograms(g.getPrograms());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onProgramSelected(ProgramProxy program) {
        placeController.goTo(new ShowProgramPlace(program));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onProgramEdit(ProgramProxy program) {
        placeController.goTo(new EditProgramPlace(program));
    }
}
