package hu.sch.kfc.client.ui.view.impl;

import java.util.List;
import hu.sch.kfc.client.ui.view.ShowView;
import hu.sch.kfc.client.ui.widget.EventBox;
import hu.sch.kfc.shared.Program;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ShowViewImpl extends Composite implements ShowView {

    public interface ShowViewImplUiBinder extends UiBinder<Widget, ShowViewImpl> {
    }

    @UiField
    SpanElement groupLabel;
    @UiField
    FlowPanel container;
    private Listener listener;

    @Inject
    public ShowViewImpl(ShowViewImplUiBinder uiBinder) {
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
    public void setPrograms(List<Program> events) {
        EventBox eb;
        for (Program e : events) {
            eb = new EventBox(e);
            container.add(eb);
        }
    }

    @Override
    public void reset() {
        container.clear();
    }
}
