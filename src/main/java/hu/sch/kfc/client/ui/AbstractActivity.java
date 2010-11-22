package hu.sch.kfc.client.ui;

import com.google.gwt.place.shared.PlaceController;

public abstract class AbstractActivity extends com.google.gwt.activity.shared.AbstractActivity {

    protected PlaceController placeController;

    public AbstractActivity(PlaceController placeController) {
        this.placeController = placeController;
    }
}
