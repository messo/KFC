package hu.sch.kfc.shared;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Egy kört reprezentál, amely rendezvényeket szervezhet, és lehet tőlük kaját rendelni.
 *
 * @author  messo
 * @since   0.1
 */
@SuppressWarnings("serial")
public class Group implements Serializable {

    private String name;
    private String token;

    private static HashMap<String, Group> groupsByToken = new HashMap<String, Group>();

    /**
     * Visszatér egy Group példánnyal, ha cachelve van a token alapján,
     * egyébként null
     *
     * @param token
     * @return cachelt objektum vagy null.
     */
    public static Group getCachedInstanceByToken(String token) {
        return groupsByToken.get(token);
    }

    public static void addToCache(List<Group> groups) {
        for (Group g : groups) {
            groupsByToken.put(g.getToken(), g);
        }
    }

    public static void addToCache(Group g) {
        groupsByToken.put(g.getToken(), g);
    }

    public Group() {
    }

    public Group(String name, String token) {
        this.name = name;
        this.token = token;
    }

    /**
     * Kör olvasható nevének lekérdezése
     *
     * @return kör neve
     */
    public String getName() {
        return name;
    }

    /**
     * Körhöz tartozó token (ami az url-ben szerepel) lekérdezése
     *
     * @return token
     */
    public String getToken() {
        return token;
    }
}
