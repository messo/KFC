package hu.sch.kfc.client.ui.view.editor;

import hu.sch.kfc.client.model.ProgramProxy;
import hu.sch.kfc.client.ui.DefaultBundle;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class ProgramEditor extends Composite implements Editor<ProgramProxy> {

    public interface MyUiBinder extends UiBinder<Widget, ProgramEditor> {
    }

    private static final MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

    /*
     * TODO - címkék-hez legyen for, csak még nem tudom, hogy hogyan.
     */
    @UiField
    TextBox name;
    @UiField
    TextArea description;
    @UiField(provided = true)
    DefaultBundle bundle = DefaultBundle.INSTANCE;

    public ProgramEditor() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
