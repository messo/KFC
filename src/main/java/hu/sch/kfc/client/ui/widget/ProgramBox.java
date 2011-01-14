package hu.sch.kfc.client.ui.widget;

import hu.sch.kfc.client.proxy.DateIntervalProxy;
import hu.sch.kfc.client.proxy.ProgramProxy;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class ProgramBox extends Composite {

    private static EventBoxUiBinder uiBinder = GWT.create(EventBoxUiBinder.class);

    interface EventBoxUiBinder extends UiBinder<HTMLPanel, ProgramBox> {
    }

    @UiField
    Element itemDesc;
    @UiField
    Element eventName;
    @UiField
    Element orderInterval;
    @UiField
    Button orderBtn;

    ProgramProxy program;

    public ProgramBox(ProgramProxy p) {
        initWidget(uiBinder.createAndBindUi(this));
        program = p;
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        eventName.setInnerText(program.getName());
        itemDesc.setInnerText(program.getDescription());
        DateIntervalProxy order = program.getOrderInterval();
        if (order.getIsEnded()) {
            orderBtn.setDisabled(true);
        }
        orderInterval.setInnerText(order.getInterval());
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        // stop the timers.
    }
}
