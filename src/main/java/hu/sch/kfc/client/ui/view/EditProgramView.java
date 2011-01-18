package hu.sch.kfc.client.ui.view;

import hu.sch.kfc.client.model.ProgramProxy;
import hu.sch.kfc.client.ui.view.editor.ProgramEditor;
import com.google.gwt.user.client.ui.IsWidget;

public interface EditProgramView extends IsWidget {

    interface Listener {
        /**
         * Akkor hívódik meg, amikor a felhasználó el akarja menteni a rendezvényt
         */
        public void onSave();
    }

    /**
     * Átadjuk a viewnak a programunkat, hogy feltölthesse a kis mezőket
     * 
     * @param program
     */
    void showProgram(ProgramProxy program);

    void setListener(Listener listener);

    ProgramEditor getEditor();
}
