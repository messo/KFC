package hu.sch.kfc.client.ui.view;

import hu.sch.kfc.shared.proxy.OrderIntervalProxy;
import hu.sch.kfc.shared.proxy.ProgramProxy;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.HasDataEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.user.client.ui.IsWidget;

public interface EditProgramView extends IsWidget, Editor<ProgramProxy> {

    @Path("name")
    IsEditor<ValueBoxEditor<String>> getNameEditor();

    @Path("description")
    IsEditor<ValueBoxEditor<String>> getDescriptionEditor();

    @Path("orderIntervals")
    HasDataEditor<OrderIntervalProxy> getOrderIntervalsEditor();

    interface Listener {
        /**
         * Akkor hívódik meg, amikor a felhasználó el akarja menteni a rendezvényt
         */
        public void onSave();
    }

    void setListener(Listener listener);
}
