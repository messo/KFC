package hu.sch.kfc.client;

import hu.sch.kfc.client.atmosphere.AtmosphereClient;
import hu.sch.kfc.client.place.AppPlaceHistoryMapper;
import hu.sch.kfc.client.place.ListPlace;
import hu.sch.kfc.client.ui.MainActivityMapper;
import hu.sch.kfc.client.ui.Shell;
import hu.sch.kfc.shared.event.LikeEvent;
import hu.sch.kfc.shared.event.LikeEventHandler;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;
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
                placeController), eventBus);
        // az eredményt, pedig a Shell-nek a mainPaneljébe rakjuk!
        activityManager.setDisplay(shell.getMainPanel());

        AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new ListPlace());

        RootPanel.get().add(shell);

        historyHandler.handleCurrentHistory();

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
