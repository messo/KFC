package hu.sch.kfc.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListGroupsPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<ListGroupsPlace> {
        @Override
        public String getToken(ListGroupsPlace place) {
            return null;
        }

        @Override
        public ListGroupsPlace getPlace(String token) {
            return new ListGroupsPlace();
        }
    }
}
