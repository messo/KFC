package hu.sch.kfc.client.ui.view;

import hu.sch.kfc.shared.proxy.ProgramProxy;
import java.util.List;
import com.google.gwt.user.client.ui.IsWidget;

public interface ShowGroupView extends IsWidget {

    /**
     * Ezt kell implementálnia az activitynek
     */
    public interface Listener {
        /**
         * Akkor hívódik meg, amikor a felhasználó kiválaszt egy eseményt, hogy megnézhesse, mit
         * lehet rendelni
         * 
         * @param program
         */
        void onProgramSelected(ProgramProxy program);

        /**
         * Akkor hívódik meg, amikor a felhasználó szerkeszteni akar egy eseményt.
         * 
         * @param program
         */
        void onProgramEdit(ProgramProxy program);
    }

    void setListener(Listener listener);

    void setText(String string);

    void setPrograms(List<ProgramProxy> events);

    void reset();
}
