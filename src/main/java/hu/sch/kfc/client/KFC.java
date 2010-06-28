package hu.sch.kfc.client;

import hu.sch.kfc.client.atmosphere.AtmosphereClient;
import hu.sch.kfc.client.event.LikeEvent;
import hu.sch.kfc.client.event.LikeEventHandler;
import hu.sch.kfc.client.place.ApplicationPlace;
import hu.sch.kfc.client.service.EventService;
import hu.sch.kfc.client.service.EventServiceAsync;
import hu.sch.kfc.client.ui.MainActivityMapper;
import hu.sch.kfc.client.ui.Shell;
import com.google.gwt.app.place.ActivityManager;
import com.google.gwt.app.place.PlaceChangeEvent;
import com.google.gwt.app.place.PlaceController;
import com.google.gwt.app.place.Activity.Display;
import com.google.gwt.app.util.IsWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KFC implements EntryPoint {

    private EventServiceAsync eventService = GWT.create(EventService.class);
    protected int like = 0;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final HandlerManager eventBus = new HandlerManager(null);
        final PlaceController<ApplicationPlace> placeController = new PlaceController<ApplicationPlace>(
                eventBus);

        final Shell shell = new Shell();

        // ez vezérli a fő activityket.
        final ActivityManager<ApplicationPlace> activityManager = new ActivityManager<ApplicationPlace>(
                new MainActivityMapper(placeController), eventBus) {

            @Override
            public void onPlaceChange(PlaceChangeEvent<ApplicationPlace> event) {
                super.onPlaceChange(event);
                ApplicationPlace place = event.getNewPlace();
                GWT.log("Place changed: " + place.toString());
                if (!place.isInvokedByHistory()) {
                    // csak akkor adjuk hozzá, ha nem eleve a lenti
                    // ValueChangeHandler-ből érkezünk ide, de eventet ne
                    // küldjünk
                    GWT.log("History token added: " + place.getBookmarkToken());
                    History.newItem(place.getBookmarkToken(), false);
                }
            }
        };

        // az eredményt, pedig a Shell-nek a mainPaneljébe rakjuk!
        activityManager.setDisplay(new Display() {

            @Override
            public void showActivityWidget(IsWidget widget) {
                shell.getMainPanel().setWidget(widget.asWidget());
            }
        });

        History.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                final String token = event.getValue();
                GWT.log("History token: " + token);
                ApplicationPlace place = ApplicationPlace
                        .getPlaceForHistoryToken(token);
                if (place != null) {
                    place.setInvokedByHistory(true);
                }
                placeController.goTo(place);
            }
        });

        /*RpcRequestBuilder rrb = new RpcRequestBuilder() {
            @Override
            protected RequestBuilder doCreate(String serviceEntryPoint) {
                return new RequestBuilder(RequestBuilder.GET,
                        serviceEntryPoint);
            }
        };
        
        ((ServiceDefTarget) eventService).setRpcRequestBuilder(rrb);

        final AsyncCallback<Event> eventCallback = new AsyncCallback<Event>() {

            @Override
            public void onSuccess(Event e) {
                if (e instanceof LikeEvent) {
                    shell.setLikeLabel(((LikeEvent) e).getLiked());
                }
                // ha lekezeltük az eventet, akkor figyeljünk tovább a többi
                // eventre.
                eventService.getEvent(this);
            }

            @Override
            public void onFailure(Throwable t) {
                eventService.getEvent(this);
            }
        };*/

        RootPanel.get().add(shell);

        String initToken = History.getToken();
        if (initToken.length() == 0) {
            History.newItem(ApplicationPlace.LIST, false);
        }

        History.fireCurrentHistoryState();

        // TODO - még így is néha van Loading Chrome-ban.
        DeferredCommand.addPause(); // ezzel kicsit késleltetünk.
        DeferredCommand.addCommand(new Command() {

        	@Override
            public void execute() {
                //eventService.getEvent(eventCallback);
            }
        });
        
        eventBus.addHandler(LikeEvent.TYPE, new LikeEventHandler() {
            
            @Override
            public void onLikeEvent(LikeEvent event) {
                shell.setLikeLabel(++like);
            }
        });
        AtmosphereClient client = new AtmosphereClient(eventBus);
    }
}
