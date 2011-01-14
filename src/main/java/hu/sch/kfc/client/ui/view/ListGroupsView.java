package hu.sch.kfc.client.ui.view;

import hu.sch.kfc.client.model.GroupProxy;
import java.util.List;
import com.google.gwt.user.client.ui.IsWidget;

public interface ListGroupsView extends IsWidget {

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
        void groupClicked(GroupProxy g);
    }

    void setListener(Listener listener);

    /**
     * Megjeleníti a megadott köröket. Ez a metódus alapállapotba hozza a viewt.
     * 
     * @param groups
     */
    void setGroups(List<GroupProxy> groups);
}
