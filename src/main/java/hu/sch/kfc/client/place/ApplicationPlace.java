package hu.sch.kfc.client.place;

import com.google.gwt.app.place.Place;

public abstract class ApplicationPlace extends Place {

    public static final String LIST = "list";
    public static final String SHOW = "show";
    private boolean invokedByHistory = false;

    public abstract String getBookmarkToken();

    public static ApplicationPlace getPlaceForHistoryToken(String token) {
        int end = token.indexOf('/');
        if (end == -1) {
            end = token.length();
        }
        String firstToken = token.substring(0, end);

        if (token.equals(LIST)) {
            return new ListPlace();
        } else if (firstToken.equals(SHOW)) {
            return new ShowPlace(token.substring(end + 1));
        }

        return null;
    }

    public void setInvokedByHistory(boolean b) {
        invokedByHistory = b;
    }

    public boolean isInvokedByHistory() {
        return invokedByHistory;
    }
}
