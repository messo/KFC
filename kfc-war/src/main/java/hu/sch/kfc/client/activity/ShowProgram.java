package hu.sch.kfc.client.activity;

import hu.sch.kfc.client.place.ShowProgramPlace;
import hu.sch.kfc.client.ui.view.ShowProgramView;
import hu.sch.kfc.shared.proxy.ProgramProxy;
import hu.sch.kfc.shared.service.KFCRequestFactory;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

/**
 * Egy adott rendezvényt mutat meg, kilistázza a rendelhető kajákat és az intervallumokat.
 * 
 * @author messo
 * @since 0.1
 */
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
