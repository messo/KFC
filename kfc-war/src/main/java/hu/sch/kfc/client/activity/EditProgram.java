package hu.sch.kfc.client.activity;

import hu.sch.kfc.client.place.EditProgramPlace;
import hu.sch.kfc.client.place.ShowGroupPlace;
import hu.sch.kfc.client.ui.view.EditProgramView;
import hu.sch.kfc.shared.proxy.ProgramProxy;
import hu.sch.kfc.shared.service.KFCRequestFactory;
import hu.sch.kfc.shared.service.ProgramRequestContext;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.requestfactory.client.RequestFactoryEditorDriver;
import com.google.gwt.requestfactory.shared.Receiver;
import com.google.gwt.requestfactory.shared.RequestContext;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

/**
 * Egy rendezvény szerkesztése, át lehet nevezni, leírást lehet adni, és meg lehet mondani, hogy
 * milyen intervallumok vannak és ott mennyi a rendelési limit, illetve, hogy az adott rendezvényen
 * mikből lehet rendelni.
 * 
 * @author messo
 * @since 0.1
 */
public class EditProgram extends AbstractActivity implements EditProgramView.Listener {

    public interface Driver extends RequestFactoryEditorDriver<ProgramProxy, EditProgramView> {
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
        // ezért kérjünk egy újat
        requestFactory.programRequest().findProgram(place.getProgramId()).with("orderIntervals")
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
        driver.initialize(requestFactory, view);

        // beállítjuk, hogy a persist() operációt kell majd végrehajtani
        // illetve megadjuk, hogy melyik proxy-t szerkessze.
        ProgramRequestContext req = requestFactory.programRequest();
        req.persist(program).with(driver.getPaths());
        driver.edit(program, req);
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
