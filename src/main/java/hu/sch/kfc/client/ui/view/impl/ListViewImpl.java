package hu.sch.kfc.client.ui.view.impl;

import java.util.List;
import hu.sch.kfc.client.ui.view.ListView;
import hu.sch.kfc.client.ui.widget.Button;
import hu.sch.kfc.shared.Group;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class ListViewImpl extends Composite implements ListView {

    private static ListViewImplUiBinder uiBinder = GWT.create(ListViewImplUiBinder.class);

    interface ListViewImplUiBinder extends UiBinder<Widget, ListViewImpl> {
    }

    private Listener listener = null;

    @UiField
    FlowPanel panel;

    public ListViewImpl() {
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
    public void setGroups(List<Group> groups) {
        panel.clear();
        Button b;
        for (final Group g : groups) {
            b = new Button(g.getName());
            b.addClickHandler(new ClickHandler() {

                @Override
                public void onClick(ClickEvent event) {
                    listener.groupClicked(g);
                }
            });
            panel.add(b);
        }
    }

}
