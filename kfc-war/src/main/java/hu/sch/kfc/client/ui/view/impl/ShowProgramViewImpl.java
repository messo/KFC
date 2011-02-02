package hu.sch.kfc.client.ui.view.impl;

import hu.sch.kfc.client.ui.view.ShowProgramView;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class ShowProgramViewImpl extends Composite implements ShowProgramView {

    public interface MyUiBinder extends UiBinder<Widget, ShowProgramViewImpl> {
    }

    @Inject
    public ShowProgramViewImpl(MyUiBinder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
