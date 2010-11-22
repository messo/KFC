package hu.sch.kfc.client.ui;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import hu.sch.kfc.client.place.ListPlace;
import hu.sch.kfc.client.place.ShowPlace;

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
