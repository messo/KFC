package hu.sch.kfc.client.activity;

import hu.sch.kfc.client.model.GroupProxy;
import hu.sch.kfc.client.model.ProgramProxy;
import hu.sch.kfc.client.place.EditProgramPlace;
import hu.sch.kfc.client.place.ShowGroupPlace;
import hu.sch.kfc.client.place.ShowProgramPlace;
import hu.sch.kfc.client.request.KFCRequestFactory;
import hu.sch.kfc.client.ui.view.ShowGroupView;
import java.util.List;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * Egy adott körhöz tartozó rendezvényeket listázza ki.
 * 
 * @author balint
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

        requestFactory.groupRequest().findGroupByToken(groupToken).fire(new Receiver<GroupProxy>() {
            @Override
            public void onSuccess(GroupProxy response) {
                setGroup(response);
            }
        });

        requestFactory.programRequest().findProgramsByGroupToken(groupToken).with("orderInterval")
                .fire(new Receiver<List<ProgramProxy>>() {
                    @Override
                    public void onSuccess(List<ProgramProxy> response) {
                        onProgramsReceived(response);
                    }
                });

        panel.setWidget(view);
    }

    protected void setGroup(GroupProxy g) {
        group = g;
        if (group != null)
            onGroupChanged();
    }

    private void onGroupChanged() {
        view.setText(group.getName());
    }

    private void onProgramsReceived(List<ProgramProxy> programs) {
        // elmentjük, hogy ha kell, akkor csoporthoz tudjuk rendelni
        // this.programs = programs;
        view.setPrograms(programs);
    }

    @Override
    public void onProgramSelected(ProgramProxy program) {
        placeController.goTo(new ShowProgramPlace(program));
    }

    @Override
    public void onProgramEdit(ProgramProxy program) {
        placeController.goTo(new EditProgramPlace(program));
    }
}
