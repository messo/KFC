package hu.sch.kfc.client.ui.widget;

import java.util.Iterator;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ButtonBar extends Composite implements HasWidgets {

    private static ButtonBarUiBinder uiBinder = GWT.create(ButtonBarUiBinder.class);

    interface ButtonBarUiBinder extends UiBinder<FlowPanel, ButtonBar> {
    }

    public ButtonBar() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public void add(Button button) {
        FlowPanel fp = (FlowPanel) getWidget();

        fp.add(button);
        final int wc = fp.getWidgetCount();

        if (wc > 1) {
            button.collapse(true, false);
            final Widget left = fp.getWidget(wc - 2);

            if (left instanceof Button) {
                ((Button) left).collapse(null, true);
            }
        }
    }

    public boolean remove(int index) {
        FlowPanel fp = (FlowPanel) getWidget();

        final boolean present = fp.remove(index);

        if (present) {
            final int cnt = fp.getWidgetCount();

            if (cnt > 0) {
                if (index == 0) {
                    // correct the new first button, since the first was removed.
                    final Widget right = fp.getWidget(0);

                    if (right instanceof Button) {
                        ((Button) right).collapse(false, null);
                    }
                } else if (index == cnt) {
                    // correct the new last button, since the last button was removed.
                    final Widget left = fp.getWidget(index - 1);

                    if (left instanceof Button) {
                        ((Button) left).collapse(null, false);
                    }
                } // else button removed in between other buttons, so nothing to do
            }
        }
        return present;
    }

    @Override
    public void add(Widget w) {
        ((FlowPanel) getWidget()).add(w);
    }

    @Override
    public void clear() {
        ((FlowPanel) getWidget()).clear();
    }

    @Override
    public Iterator<Widget> iterator() {
        return ((FlowPanel) getWidget()).iterator();
    }

    @Override
    public boolean remove(Widget w) {
        return ((FlowPanel) getWidget()).remove(w);
    }
}
