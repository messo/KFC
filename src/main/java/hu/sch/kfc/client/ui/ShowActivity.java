package hu.sch.kfc.client.ui;

import java.util.List;
import hu.sch.kfc.client.cache.CachingAsyncCallback;
import hu.sch.kfc.client.place.ShowPlace;
import hu.sch.kfc.client.service.ProgramServiceAsync;
import hu.sch.kfc.client.service.GroupServiceAsync;
import hu.sch.kfc.client.ui.view.ShowView;
import hu.sch.kfc.shared.Program;
import hu.sch.kfc.shared.Group;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * Egy adott körhöz tartozó rendezvényeket listázza ki.
 * 
 * @author balint
 * @since 0.1
 */
public class ShowActivity extends AbstractActivity implements ShowView.Listener {

    @Inject
    private ShowView view;
    @Inject
    private GroupServiceAsync groupService;
    @Inject
    private ProgramServiceAsync eventService;

    private String groupToken;
    private Group group = null;
    private List<Program> programs;

    final Timer programSetter = new Timer() {
        public void run() {
            GWT.log("timer...");
            if (group != null) {
                programSetter.cancel();
                group.setPrograms(programs);
            }
        }
    };

    public Activity withPlace(ShowPlace place) {
        this.groupToken = place.getGroupToken();
        return this;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setListener(this);
        view.reset();

        groupService.getGroupByToken(groupToken, new CachingAsyncCallback<Group>() {

            @Override
            public void onRetrieved(Group g) {
                setGroup(g);
            }

            @Override
            public void onFailure(Throwable arg0) {
                Window.alert("Hiba a kérésben!");
            }
        });

        // lekérjük az eventeket.
        if (group != null && group.getPrograms() != null) {
            onHavingPrograms(group.getPrograms());
        } else {
            eventService.getEventsForGroupToken(groupToken, new AsyncCallback<List<Program>>() {

                @Override
                public void onSuccess(List<Program> result) {
                    onProgramsReceived(result);
                }

                @Override
                public void onFailure(Throwable caught) {
                }
            });
        }

        panel.setWidget(view);
    }

    protected void setGroup(Group g) {
        group = g;
        if (group != null)
            onGroupChanged();
    }

    private void onGroupChanged() {
        view.setText(group.getName());
    }

    private void onHavingPrograms(List<Program> programs) {
        // elmentjük, hogy ha kell, akkor csoporthoz tudjuk rendelni
        this.programs = programs;
        view.setPrograms(programs);
    }

    private void onProgramsReceived(final List<Program> programs) {
        onHavingPrograms(programs);
        programSetter.scheduleRepeating(1000);
    }
}
