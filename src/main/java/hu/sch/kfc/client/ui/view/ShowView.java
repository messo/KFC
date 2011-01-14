package hu.sch.kfc.client.ui.view;

import hu.sch.kfc.client.proxy.ProgramProxy;
import java.util.List;
import com.google.gwt.user.client.ui.IsWidget;

public interface ShowView extends IsWidget {

    /**
     * Ezt kell implementálnia a tulajdonosnak.
     */
    public interface Listener {
    }

    void setListener(Listener listener);

    void setText(String string);

    void setPrograms(List<ProgramProxy> events);

    void reset();
}
