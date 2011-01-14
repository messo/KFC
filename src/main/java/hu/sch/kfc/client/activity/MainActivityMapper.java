package hu.sch.kfc.client.activity;

import hu.sch.kfc.client.place.ListGroupsPlace;
import hu.sch.kfc.client.place.ShowGroupPlace;
import hu.sch.kfc.client.place.ShowProgramPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MainActivityMapper implements ActivityMapper {

    @Inject
    private Provider<ListGroups> listGroupsActivityProvider;
    @Inject
    private Provider<ShowGroup> showGroupActivityProvider;
    @Inject
    private Provider<ShowProgram> showProgramActivityProvider;

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof ListGroupsPlace) {
            return listGroupsActivityProvider.get();
        } else if (place instanceof ShowGroupPlace) {
            return showGroupActivityProvider.get().withPlace((ShowGroupPlace) place);
        } else if (place instanceof ShowProgramPlace) {
            return showProgramActivityProvider.get().withPlace((ShowProgramPlace) place);
        }

        return null;
    }
}
