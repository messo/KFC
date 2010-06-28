package hu.sch.kfc.client.ui.widget;

import hu.sch.kfc.shared.Program;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;


public class EventBox extends Composite {

    private static EventBoxUiBinder uiBinder = GWT.create(EventBoxUiBinder.class);
    private static EventBox instance = null;

    interface EventBoxUiBinder extends UiBinder<HTMLPanel, EventBox> {
    }

    @UiField
    DivElement itemHeader;
    @UiField
    DivElement itemDesc;

    private EventBox() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public static EventBox getInstance() {
        if( instance == null ) {
            instance = new EventBox();
        }
        return instance;
    }

    public String displayEvent(Program e) {
        //itemHeader.setInnerText(e.getName());
        //itemDesc.setInnerText(e.getDescription());
        return getElement().getInnerHTML();
    }

}
