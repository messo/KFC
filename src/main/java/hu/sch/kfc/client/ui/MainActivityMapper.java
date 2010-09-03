package hu.sch.kfc.client.ui;

import hu.sch.kfc.client.place.ListPlace;
import hu.sch.kfc.client.place.ShowPlace;
import com.google.gwt.app.place.Activity;
import com.google.gwt.app.place.ActivityMapper;
import com.google.gwt.app.place.Place;
import com.google.gwt.app.place.PlaceController;

public class MainActivityMapper implements ActivityMapper {

    private PlaceController placeController;

    public MainActivityMapper(PlaceController placeController) {
        this.placeController = placeController;
    }

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof ListPlace) {
            return new ListActivity(placeController);
        } else if (place instanceof ShowPlace) {
            return new ShowActivity(placeController, ((ShowPlace)place).getGroupToken());
        }

        return null;
    }
}
