package hu.sch.kfc.client.ui.view;

import hu.sch.kfc.client.model.ProgramProxy;
import java.util.List;
import com.google.gwt.user.client.ui.IsWidget;

public interface ShowGroupView extends IsWidget {

    /**
     * Ezt kell implementálnia a tulajdonosnak.
     */
    public interface Listener {
        void onProgramSelected(ProgramProxy program);
    }

    void setListener(Listener listener);

    void setText(String string);

    void setPrograms(List<ProgramProxy> events);

    void reset();
}
