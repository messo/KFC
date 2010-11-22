package hu.sch.kfc.client;

import hu.sch.kfc.client.atmosphere.AtmosphereClient;
import hu.sch.kfc.client.place.ApplicationPlace;
import hu.sch.kfc.client.ui.MainActivityMapper;
import hu.sch.kfc.client.ui.Shell;
import hu.sch.kfc.shared.event.LikeEvent;
import hu.sch.kfc.shared.event.LikeEventHandler;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.place.shared.PlaceChangeEvent;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class KFC implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        final EventBus eventBus = new SimpleEventBus();
        final PlaceController placeController = new PlaceController(eventBus);

        final Shell shell = new Shell();

        // ez vezérli a fő activityket.
        final ActivityManager activityManager = new ActivityManager(new MainActivityMapper(
                placeController), eventBus) {

            @Override
            public void onPlaceChange(PlaceChangeEvent event) {
                super.onPlaceChange(event);
                ApplicationPlace place = (ApplicationPlace) event.getNewPlace();
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
        activityManager.setDisplay(shell.getMainPanel());

        History.addValueChangeHandler(new ValueChangeHandler<String>() {

            @Override
            public void onValueChange(ValueChangeEvent<String> event) {
                final String token = event.getValue();
                GWT.log("History token: " + token);
                ApplicationPlace place = ApplicationPlace.getPlaceForHistoryToken(token);
                if (place != null) {
                    place.setInvokedByHistory(true);
                }
                placeController.goTo(place);
            }
        });

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
                AtmosphereClient client = new AtmosphereClient(eventBus);
            }
        });

        eventBus.addHandler(LikeEvent.TYPE, new LikeEventHandler() {

            @Override
            public void onLikeEvent(LikeEvent event) {
                shell.setLikeLabel(event.getLiked());
            }
        });
    }
}
