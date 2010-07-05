package hu.sch.kfc.client.ui;

import java.util.List;
import hu.sch.kfc.client.cache.CachingAsyncCallback;
import hu.sch.kfc.client.place.ApplicationPlace;
import hu.sch.kfc.client.service.ProgramService;
import hu.sch.kfc.client.service.ProgramServiceAsync;
import hu.sch.kfc.client.service.GroupService;
import hu.sch.kfc.client.service.GroupServiceAsync;
import hu.sch.kfc.client.ui.view.ShowView;
import hu.sch.kfc.client.ui.view.impl.ShowViewImpl;
import hu.sch.kfc.shared.Program;
import hu.sch.kfc.shared.Group;
import com.google.gwt.app.place.PlaceController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ShowActivity extends AbstractActivity implements ShowView.Listener {

    private static ShowView defaultView = null;
    private ShowView view;
    private String groupToken;
    private Group group = null;
    private List<Program> programs;
    private GroupServiceAsync groupService = GWT.create(GroupService.class);
    private ProgramServiceAsync eventService = GWT.create(ProgramService.class);

    final Timer programSetter = new Timer() {
        public void run() {
            if( group != null ) {
                programSetter.cancel();
                group.setPrograms(programs);
            }
        }
    };
    
    public ShowActivity(PlaceController<ApplicationPlace> placeController, String token,
            ShowView view) {
        super(placeController);
        this.view = view;
        this.groupToken = token;
        view.setListener(this);
    }

    public ShowActivity(PlaceController<ApplicationPlace> placeController, String token) {
        this(placeController, token, getDefaultView());
    }

    private static ShowView getDefaultView() {
        if (defaultView == null) {
            defaultView = new ShowViewImpl();
        }
        return defaultView;
    }

    @Override
    public void start(Display panel) {
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

        panel.showActivityWidget(view);
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
        view.setEvents(programs);
    }

    private void onProgramsReceived(final List<Program> programs) {
        onHavingPrograms(programs);
        programSetter.scheduleRepeating(1000);
    }

}
