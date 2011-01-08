package hu.sch.kfc.client.ui;

import hu.sch.kfc.client.gin.KFCGinModule;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

/**
 * Minden {@link Activity}nek elérhető a {@link PlaceController}, mellyel a különböző helyekre
 * tudunk navigálni, ezzel különböző {@link Activity}-ket tudunk elindítani. <b>Fontos</b>: Mivel a
 * widgetek létrehozása viszonylag költséges, ezért az {@link Activity}-hez tartozó viewból egyetlen
 * egy példányt hozunk létre (lásd {@link KFCGinModule}), ebből kifolyólag az {@link Activity}nek
 * szem előtt kell tartania, hogy a view lehet, hogy nincs alapállapotban. Ehhez a viewnak
 * szolgáltatnia kell egy <tt>reset()</tt> metódust, amennyiben szükséges.
 * 
 * @author balint
 * @since 0.1
 */
public abstract class AbstractActivity extends com.google.gwt.activity.shared.AbstractActivity {

    @Inject
    protected PlaceController placeController;
}
