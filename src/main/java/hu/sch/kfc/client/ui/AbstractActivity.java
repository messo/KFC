package hu.sch.kfc.client.ui;

import hu.sch.kfc.client.place.ApplicationPlace;
import com.google.gwt.app.place.PlaceController;

public abstract class AbstractActivity extends com.google.gwt.app.place.AbstractActivity {

    protected PlaceController<ApplicationPlace> placeController;

    public AbstractActivity(PlaceController<ApplicationPlace> placeController) {
        this.placeController = placeController;
    }
}
