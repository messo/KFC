package hu.sch.kfc.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;

public class ListPlace extends Place {

    public static class Tokenizer implements PlaceTokenizer<ListPlace> {
        @Override
        public String getToken(ListPlace place) {
            return null;
        }

        @Override
        public ListPlace getPlace(String token) {
            return new ListPlace();
        }
    }
}
