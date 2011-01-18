package hu.sch.kfc.client.ui.view.impl;

import hu.sch.kfc.client.model.ProgramProxy;
import hu.sch.kfc.client.ui.DefaultBundle;
import hu.sch.kfc.client.ui.view.EditProgramView;
import hu.sch.kfc.client.ui.view.editor.ProgramEditor;
import hu.sch.kfc.client.ui.widget.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class EditProgramViewImpl extends Composite implements EditProgramView {

    public interface MyUiBinder extends UiBinder<Widget, EditProgramViewImpl> {
    }

    @UiField
    ProgramEditor editor;
    @UiField
    Button save;
    @UiField(provided = true)
    final DefaultBundle bundle = DefaultBundle.INSTANCE;

    private Listener listener;

    @Override
    public ProgramEditor getEditor() {
        return editor;
    }

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @UiHandler("save")
    public void onSave(ClickEvent event) {
        listener.onSave();
    }

    @Inject
    public EditProgramViewImpl(MyUiBinder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void showProgram(ProgramProxy program) {
        // TODO Auto-generated method stub
    }
}
