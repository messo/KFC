package hu.sch.kfc.client.ui.widget;

import hu.sch.kfc.client.model.DateIntervalProxy;
import hu.sch.kfc.client.model.ProgramProxy;
import hu.sch.kfc.client.ui.view.ShowGroupView;
import hu.sch.kfc.client.ui.view.ShowGroupView.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
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
    @UiField
    Button editBtn;
    @UiField
    ButtonBar btnBar;

    private ProgramProxy program;
    private Listener listener;

    public ProgramBox(ProgramProxy p, ShowGroupView.Listener listener) {
        initWidget(uiBinder.createAndBindUi(this));
        program = p;
        this.listener = listener;
    }

    @UiHandler("orderBtn")
    public void onSelected(ClickEvent click) {
        listener.onProgramSelected(program);
    }

    @UiHandler("editBtn")
    public void onEdit(ClickEvent click) {
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
