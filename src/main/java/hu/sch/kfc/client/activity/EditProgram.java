package hu.sch.kfc.client.activity;

import hu.sch.kfc.client.model.ProgramProxy;
import hu.sch.kfc.client.place.EditProgramPlace;
import hu.sch.kfc.client.place.ShowGroupPlace;
import hu.sch.kfc.client.request.KFCRequestFactory;
import hu.sch.kfc.client.request.ProgramRequest;
import hu.sch.kfc.client.ui.view.EditProgramView;
import hu.sch.kfc.client.ui.view.editor.ProgramEditor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

public class EditProgram extends AbstractActivity implements EditProgramView.Listener {

    public interface Driver extends RequestFactoryEditorDriver<ProgramProxy, ProgramEditor> {
    }

    @Inject
    private EditProgramView view;
    @Inject
    private KFCRequestFactory requestFactory;
    @Inject
    private Driver driver;
    private ProgramProxy program;

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        view.setListener(this);
        panel.setWidget(view);
    }

    public EditProgram withPlace(EditProgramPlace place) {
        // program = place.getProgram();
        // if (program == null) {
        // // csak ID van, keressük meg a példányt.
        // requestFactory.programRequest().findProgram(place.getProgramId())
        // .fire(new Receiver<ProgramProxy>() {
        // @Override
        // public void onSuccess(ProgramProxy response) {
        // program = response;
        // startForReal();
        // }
        // });
        // } else {
        // startForReal();
        // }

        // egyelőre nem tudjuk rendesen felhasználni a kapott program proxyt,
        // ezért kérjünk egy újat, de orderInterval nélkül.
        requestFactory.programRequest().findProgram(place.getProgramId())
                .fire(new Receiver<ProgramProxy>() {
                    @Override
                    public void onSuccess(ProgramProxy response) {
                        program = response;
                        startForReal();
                    }
                });

        return this;
    }

    /**
     * Itt indul a buli igazán, mert most már van rendezvényünk.
     */
    private void startForReal() {
        // driver-t inicializáljuk
        driver.initialize(requestFactory, view.getEditor());

        // beállítjuk, hogy a persist() operációt kell majd végrehajtani
        // illetve megadjuk, hogy melyik proxy-t szerkessze.
        ProgramRequest req = requestFactory.programRequest();
        req.persist().using(program);
        driver.edit(program, req);

        // egyebek.
        view.showProgram(program);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSave() {
        RequestContext context = driver.flush();

        if (driver.hasErrors()) {
            for (EditorError ee : driver.getErrors()) {
                GWT.log(ee.getMessage());
            }
            return;
        }

        context.fire(new Receiver<Void>() {

            @Override
            public void onSuccess(Void response) {
                placeController.goTo(new ShowGroupPlace(program.getGroupToken()));
            }
        });
    }
}
