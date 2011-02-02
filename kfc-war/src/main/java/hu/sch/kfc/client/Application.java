package hu.sch.kfc.client;

import hu.sch.kfc.client.gin.KFCGinjector;
import hu.sch.kfc.client.ui.DefaultBundle;
import hu.sch.kfc.client.ui.Shell;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.dom.client.StyleInjector;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;

/**
 * Mivel a {@link KFC} modul példányát nem mi hozzuk létre, hiszen annak létre kell jönnie a
 * belépési ponthoz, ezért ott a Gin-t se lehet még használni, kell egy másik példány, aminél már
 * használhatjuk a Gin-t és erre szolgál ez az osztály. Ezt példányosítjuk a {@link KFC} modulban,
 * de nem kézzel, hanem a {@link KFCGinjector}-t kérjük fel erre a feladatra, így a konstruktorba
 * már injektálhatjuk a vezérlőelemeket.
 * <p>
 * Nem történik komolyabb összetett feladat, a rendszer fő elemeit hozzuk létre, inicializáljuk
 * őket, és elindul az alkalmazás életciklusa.
 * </p>
 * 
 * @author messo
 * @since 0.1
 */
public class Application {

    /**
     * Maga az alkalmazás elindítása, működtetők létrehozása.
     * <p>
     * <b>Fontos</b>: A nem használt paramétereket ne vegyük ki, mert a létrehozásuk során fontos
     * dolgok történnek, amiknek meg kell történniük.
     * </p>
     * 
     * @param eventBus
     * @param manager
     * @param historyHandler
     */
    @Inject
    public Application(EventBus eventBus, ActivityManager manager,
            PlaceHistoryHandler historyHandler, final Shell shell) {

        RootPanel.get().add(shell);

        historyHandler.handleCurrentHistory();

        StyleInjector.inject(DefaultBundle.INSTANCE.defStyle().getText());

        // TODO - még így is néha van Loading Chrome-ban.
        // Scheduler.get().scheduleDeferred(new ScheduledCommand() {
        //
        // @Override
        // public void execute() {
        // // AtmosphereClient client = new AtmosphereClient(eventBus);
        // }
        // });
        //
        // eventBus.addHandler(LikeEvent.TYPE, new LikeEventHandler() {
        //
        // @Override
        // public void onLikeEvent(LikeEvent event) {
        // shell.setLikeLabel(event.getLiked());
        // }
        // });
    }
}
