package hu.sch.kfc.client.gin;

import hu.sch.kfc.client.Application;
import hu.sch.kfc.client.place.AppPlaceHistoryMapper;
import hu.sch.kfc.client.place.ListPlace;
import hu.sch.kfc.client.request.KFCRequestFactory;
import hu.sch.kfc.client.ui.ListActivity;
import hu.sch.kfc.client.ui.MainActivityMapper;
import hu.sch.kfc.client.ui.Shell;
import hu.sch.kfc.client.ui.ShowActivity;
import hu.sch.kfc.client.ui.view.ListView;
import hu.sch.kfc.client.ui.view.ShowView;
import hu.sch.kfc.client.ui.view.impl.ListViewImpl;
import hu.sch.kfc.client.ui.view.impl.ShowViewImpl;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.GinModule;
import com.google.gwt.inject.client.binder.GinBinder;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * A Dependency Injectionhöz itt mondjuk meg a szabályokat, hogy mely objektumokat, hogy injektáljon
 * be a megfelelő helyre.
 * 
 * @author messo
 * @since 0.1
 */
public class KFCGinModule implements GinModule {

    @Override
    public void configure(GinBinder binder) {
        binder.bind(Application.class);
        binder.bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        binder.bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class).in(Singleton.class);

        binder.bind(Shell.class).in(Singleton.class);
        binder.bind(ListView.class).to(ListViewImpl.class).in(Singleton.class);
        binder.bind(ShowView.class).to(ShowViewImpl.class).in(Singleton.class);
        binder.bind(ActivityMapper.class).to(MainActivityMapper.class).in(Singleton.class);

        binder.bind(ListActivity.class);
        binder.bind(ShowActivity.class);
    }

    @Provides
    @Singleton
    KFCRequestFactory getRequestFactory(EventBus eventBus) {
        KFCRequestFactory kfcRequestFactory = GWT.create(KFCRequestFactory.class);
        kfcRequestFactory.initialize(eventBus);
        return kfcRequestFactory;
    }

    @Provides
    @Singleton
    public PlaceController getPlaceController(EventBus eventBus) {
        return new PlaceController(eventBus);
    }

    @Provides
    @Singleton
    public PlaceHistoryHandler getHistoryHandler(PlaceHistoryMapper historyMapper,
            PlaceController placeController, EventBus eventBus) {
        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, new ListPlace());
        return historyHandler;
    }

    @Provides
    @Singleton
    public ActivityManager getActivityManager(ActivityMapper mapper, EventBus eventBus, Shell shell) {
        ActivityManager activityManager = new ActivityManager(mapper, eventBus);
        activityManager.setDisplay(shell.getMainPanel());
        return activityManager;
    }
}
