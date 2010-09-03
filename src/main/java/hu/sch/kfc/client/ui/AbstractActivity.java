package hu.sch.kfc.client.ui;

import com.google.gwt.app.place.PlaceController;

public abstract class AbstractActivity extends com.google.gwt.app.place.AbstractActivity {

    protected PlaceController placeController;

    public AbstractActivity(PlaceController placeController) {
        this.placeController = placeController;
    }
}
