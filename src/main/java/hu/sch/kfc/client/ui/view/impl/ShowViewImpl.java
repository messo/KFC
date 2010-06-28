package hu.sch.kfc.client.ui.view.impl;

import java.util.List;
import hu.sch.kfc.client.ui.view.ShowView;
import hu.sch.kfc.client.ui.widget.EventBox;
import hu.sch.kfc.shared.Program;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;


public class ShowViewImpl extends Composite implements ShowView {

    private static ShowViewImplUiBinder uiBinder = GWT.create(ShowViewImplUiBinder.class);

    interface ShowViewImplUiBinder extends UiBinder<Widget, ShowViewImpl> {
    }

    @UiField
    SpanElement groupLabel;
    @UiField
    DivElement container;
    private Listener listener;

    public ShowViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public Widget asWidget() {
        return this;
    }

    @Override
    public void setText(String string) {
        groupLabel.setInnerText(string);
    }

    @Override
    public void setEvents(List<Program> events) {
        StringBuilder sb = new StringBuilder();
        EventBox eb = EventBox.getInstance();
        for(Program e : events) {
            sb.append(eb.displayEvent(e));
        }
        container.setInnerHTML(sb.toString());
    }

}
