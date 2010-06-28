package hu.sch.kfc.client.ui.view;

import hu.sch.kfc.shared.Group;
import java.util.List;
import com.google.gwt.app.util.IsWidget;

public interface ListView extends IsWidget {

    /**
     * Ezt kell implementálnia a tulajdonosnak.
     */
    public interface Listener {

        /**
         * Akkor fut le, amikor a listából kiválasztunk egy kört. Praktikusan itt az a teendő, hogy
         * a usert a kör oldalára irányítsuk.
         *
         * @param g a kör, amit kiválasztottunk
         */
        void groupClicked(Group g);
    }

    void setListener(Listener listener);

    void setGroups(List<Group> groups);
}
