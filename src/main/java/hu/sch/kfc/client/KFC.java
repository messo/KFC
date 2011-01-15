package hu.sch.kfc.client;

import hu.sch.kfc.client.activity.AbstractActivity;
import hu.sch.kfc.client.activity.MainActivityMapper;
import hu.sch.kfc.client.gin.KFCGinjector;
import hu.sch.kfc.client.ui.Shell;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;

/**
 * Az egész KFC alkalmazás belépőpontja, innen indul az egész finomság. Nem történik nagy varázslat,
 * kérünk a GIN-től egy {@link Application}-t, ami létrehozza a workflowhoz szükséges
 * vezérlőelemeket ({@link ActivityManager}, {@link PlaceHistoryHandler}) illetve az alkalmazás keretét jelentő
 * {@link Shell} widgetet.
 * <p>
 * A workflowt már az {@link ActivityManager} vezérli, aki a {@link MainActivityMapper}
 * segítségével az adott {@link Place}-ekhez, elindítja a megfelelő {@link Activity}-t, aki kezeli a
 * saját kis View-ját.
 * </p>
 * <p>
 * Minden {@link AbstractActivity}-ben elérhető a {@link PlaceController}, ezzel tudunk más helyekre
 * navigálni, ezzel egy másik {@link Activity}-t elindítani.
 * </p>
 * 
 * @author messo
 */
public class KFC implements EntryPoint {

    /**
     * Belépési pont
     */
    public void onModuleLoad() {
        // GIN-nel oldunk meg mindent.
        ((KFCGinjector) GWT.create(KFCGinjector.class)).getApplication();
    }
}
