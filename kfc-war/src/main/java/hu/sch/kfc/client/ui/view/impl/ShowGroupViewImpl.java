package hu.sch.kfc.client.ui.view.impl;

import hu.sch.kfc.client.ui.DefaultBundle;
import hu.sch.kfc.client.ui.view.ShowGroupView;
import hu.sch.kfc.client.ui.widget.ProgramBox;
import hu.sch.kfc.shared.proxy.ProgramProxy;
import java.util.List;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ShowGroupViewImpl extends Composite implements ShowGroupView {

    public interface MyUiBinder extends UiBinder<Widget, ShowGroupViewImpl> {
    }

    @UiField
    SpanElement groupLabel;
    @UiField
    FlowPanel container;
    @UiField(provided = true)
    final DefaultBundle bundle = DefaultBundle.INSTANCE;
    
    private Listener listener;

    @Inject
    public ShowGroupViewImpl(MyUiBinder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override
    public void setText(String string) {
        groupLabel.setInnerText(string);
    }

    @Override
    public void setPrograms(List<ProgramProxy> programs) {
        ProgramBox eb;
        for (ProgramProxy e : programs) {
            eb = new ProgramBox(e, listener);
            container.add(eb);
        }
    }

    @Override
    public void reset() {
        container.clear();
    }
}
