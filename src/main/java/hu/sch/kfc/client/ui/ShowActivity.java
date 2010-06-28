package hu.sch.kfc.client.ui;

import java.util.List;
import hu.sch.kfc.client.place.ApplicationPlace;
import hu.sch.kfc.client.place.ShowPlace;
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
import com.google.gwt.user.client.rpc.AsyncCallback;

public class ShowActivity extends AbstractActivity implements ShowView.Listener {

    private static ShowView defaultView = null;
    private ShowView view;
    private Group group;
    private GroupServiceAsync groupService = GWT.create(GroupService.class);
    private ProgramServiceAsync eventService = GWT.create(ProgramService.class);

    public ShowActivity(PlaceController<ApplicationPlace> placeController, Group group, ShowView view) {
        super(placeController);
        this.view = view;
        this.group = group;
        view.setListener(this);
    }

    public ShowActivity(PlaceController<ApplicationPlace> placeController, Group group) {
        this(placeController, group, getDefaultView());
    }

    private static ShowView getDefaultView() {
        if (defaultView == null) {
            defaultView = new ShowViewImpl();
        }
        return defaultView;
    }

    @Override
    public void start(Display panel) {
        GWT.log("Activity start: show");

        // megnézzük, hogy mi a csoport tokenje.
        String token = null;
        ApplicationPlace place = placeController.getWhere();
        token = ((ShowPlace) place).getGroupToken();

        // lekérjük az eventeket.
        eventService.getEventsForGroupToken(token, new AsyncCallback<List<Program>>() {

            @Override
            public void onSuccess(List<Program> result) {
                eventsReceived(result);
            }

            @Override
            public void onFailure(Throwable caught) {
            }
        });

        // TODO - ha nincs csoport, akkor azt is kérjük le, mert itt megjeleníthetnénk tök jó dolgokat.
        if( group != null ) {
            view.setText(group.getName());
        }

        panel.showActivityWidget(view);
    }

    private void eventsReceived(List<Program> events) {
        view.setEvents(events);
    }

}
