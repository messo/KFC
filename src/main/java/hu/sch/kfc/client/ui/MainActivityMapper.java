package hu.sch.kfc.client.ui;

import hu.sch.kfc.client.place.ListPlace;
import hu.sch.kfc.client.place.ShowPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MainActivityMapper implements ActivityMapper {

    @Inject
    private Provider<ListActivity> listActivityProvider;
    @Inject
    private Provider<ShowActivity> showActivityProvider;

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof ListPlace) {
            return listActivityProvider.get();
        } else if (place instanceof ShowPlace) {
            return showActivityProvider.get().withPlace((ShowPlace) place);
        }

        return null;
    }
}
