package hu.sch.kfc.client.ui.widget;

import hu.sch.kfc.shared.DateInterval;
import hu.sch.kfc.shared.Program;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

public class EventBox extends Composite {

    private static final DateTimeFormat df = DateTimeFormat.getFormat("yyyy. MM. dd.");
    private static final DateTimeFormat tf = DateTimeFormat.getFormat(PredefinedFormat.TIME_SHORT);

    private static EventBoxUiBinder uiBinder = GWT.create(EventBoxUiBinder.class);

    interface EventBoxUiBinder extends UiBinder<HTMLPanel, EventBox> {
    }

    @UiField
    Element itemDesc;
    @UiField
    Element eventName;
    @UiField
    Element orderInterval;
    @UiField
    Button orderBtn;

    Program program;

    public EventBox(Program p) {
        initWidget(uiBinder.createAndBindUi(this));
        program = p;
    }

    @Override
    protected void onAttach() {
        super.onAttach();
        eventName.setInnerText(program.getName());
        itemDesc.setInnerText(program.getDescription());
        DateInterval order = program.getOrderInterval();
        StringBuilder sb = new StringBuilder();
        if (order.isOneDayLong()) {
            sb.append(df.format(order.getStart())).append(" ");
            sb.append(tf.format(order.getStart())).append("-").append(tf.format(order.getEnd()));
        } else {
            sb.append("hosszú :D");
        }
        if (order.isEnded()) {
            orderBtn.setDisabled(true);
        }
        orderInterval.setInnerText(sb.toString());
    }

    @Override
    protected void onDetach() {
        super.onDetach();
        // stop the timers.
    }
}
