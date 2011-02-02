package hu.sch.kfc.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class ShowGroupPlace extends Place {

    private final String groupToken;

    public ShowGroupPlace(String groupToken) {
        this.groupToken = groupToken;
    }

    public String getGroupToken() {
        return groupToken;
    }

    @Prefix(value = "show")
    public static class Tokenizer implements PlaceTokenizer<ShowGroupPlace> {
        @Override
        public String getToken(ShowGroupPlace place) {
            return place.getGroupToken();
        }

        @Override
        public ShowGroupPlace getPlace(String token) {
            return new ShowGroupPlace(token);
        }
    }
}
