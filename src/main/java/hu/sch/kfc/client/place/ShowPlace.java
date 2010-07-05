package hu.sch.kfc.client.place;

public class ShowPlace extends ApplicationPlace {

    private final String groupToken;

    public ShowPlace(String groupToken) {
        this.groupToken = groupToken;
    }

    @Override
    public String getBookmarkToken() {
        return new StringBuilder(SHOW).append('/').append(groupToken).toString();
    }

    public String getGroupToken() {
        return groupToken;
    }
}
