package hu.sch.kfc.client.place;

import hu.sch.kfc.shared.Group;


public class ShowPlace extends ApplicationPlace {

    private final String groupToken;
    private Group group;

    public ShowPlace(String groupToken) {
        this.groupToken = groupToken;
        group = Group.getCachedInstanceByToken(groupToken);
    }

    public ShowPlace(Group g) {
        groupToken = g.getToken();
        group = g;
    }

    @Override
    public String getBookmarkToken() {
        return new StringBuilder(SHOW).append('/').append(groupToken).toString();
    }

    public Group getGroup() {
        return group;
    }

    public String getGroupToken() {
        return groupToken;
    }

}
