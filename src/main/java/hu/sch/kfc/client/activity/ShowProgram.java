package hu.sch.kfc.client.activity;

import hu.sch.kfc.client.model.ProgramProxy;
import hu.sch.kfc.client.place.ShowProgramPlace;
import hu.sch.kfc.client.request.KFCRequestFactory;
import hu.sch.kfc.client.ui.view.ShowProgramView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class ShowProgram extends AbstractActivity {

    @Inject
    private ShowProgramView view;
    @Inject
    private KFCRequestFactory requestFactory;

    private ProgramProxy program;

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        panel.setWidget(view);
    }

    public ShowProgram withPlace(ShowProgramPlace place) {
        program = place.getProgram();
        if (program == null) {
            // csak ID van, keressük meg a példányt.
            requestFactory.programRequest().findProgram(place.getProgramId())
                    .fire(new Receiver<ProgramProxy>() {
                        @Override
                        public void onSuccess(ProgramProxy response) {
                            program = response;
                        }
                    });
        }
        return this;
    }
}
