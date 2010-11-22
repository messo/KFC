package hu.sch.kfc.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ShowPlace extends Place {

    private final String groupToken;

    public ShowPlace(String groupToken) {
        this.groupToken = groupToken;
    }

    public String getGroupToken() {
        return groupToken;
    }

    @Prefix(value = "show")
    public static class Tokenizer implements PlaceTokenizer<ShowPlace> {
        @Override
        public String getToken(ShowPlace place) {
            return place.getGroupToken();
        }

        @Override
        public ShowPlace getPlace(String token) {
            return new ShowPlace(token);
        }
    }
}
