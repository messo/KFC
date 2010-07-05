package hu.sch.kfc.client.ui;

import hu.sch.kfc.client.place.ApplicationPlace;
import hu.sch.kfc.client.place.ListPlace;
import hu.sch.kfc.client.place.ShowPlace;
import com.google.gwt.app.place.Activity;
import com.google.gwt.app.place.ActivityMapper;
import com.google.gwt.app.place.PlaceController;

public class MainActivityMapper implements ActivityMapper<ApplicationPlace> {

    private PlaceController<ApplicationPlace> placeController;

    public MainActivityMapper(PlaceController<ApplicationPlace> placeController) {
        this.placeController = placeController;
    }

    @Override
    public Activity getActivity(ApplicationPlace place) {
        if (place instanceof ListPlace) {
            return new ListActivity(placeController);
        } else if (place instanceof ShowPlace) {
            return new ShowActivity(placeController, ((ShowPlace)place).getGroupToken());
        }

        return null;
    }

}
