package hu.sch.kfc.client.ui.view.impl;

import hu.sch.kfc.client.model.OrderIntervalProxy;
import hu.sch.kfc.client.ui.DefaultBundle;
import hu.sch.kfc.client.ui.view.EditProgramView;
import hu.sch.kfc.client.ui.widget.Button;
import java.util.Date;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.HasDataEditor;
import com.google.gwt.editor.ui.client.adapters.ValueBoxEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class EditProgramViewImpl extends Composite implements EditProgramView {

    public interface MyUiBinder extends UiBinder<Widget, EditProgramViewImpl> {
    }

    @UiField
    TextBox name;
    @UiField
    TextArea description;
    @UiField
    CellTable<OrderIntervalProxy> table;

    @UiField
    Button save;
    @UiField(provided = true)
    final DefaultBundle bundle = DefaultBundle.INSTANCE;

    private Listener listener;
    private HasDataEditor<OrderIntervalProxy> orderIntervalsEditor;

    @Inject
    public EditProgramViewImpl(MyUiBinder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        orderIntervalsEditor = HasDataEditor.of(table);

        initTable();
    }

    @Override
    public IsEditor<ValueBoxEditor<String>> getNameEditor() {
        return name;
    }

    @Override
    public IsEditor<ValueBoxEditor<String>> getDescriptionEditor() {
        return description;
    }

    @Override
    public HasDataEditor<OrderIntervalProxy> getOrderIntervalsEditor() {
        return orderIntervalsEditor;
    }

    @Override
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @UiHandler("save")
    public void onSave(ClickEvent event) {
        listener.onSave();
    }

    /**
     * Létrehozzuk az intervallumok megjelenítésére szolgáló táblázatot
     */
    private void initTable() {
        table.addColumn(
                new Column<OrderIntervalProxy, Date>(new DatePickerCell(DateTimeFormat
                        .getFormat(PredefinedFormat.DATE_TIME_FULL))) {

                    @Override
                    public Date getValue(OrderIntervalProxy object) {
                        return object.getStart();
                    }
                }, "Kezdés");

        table.addColumn(
                new Column<OrderIntervalProxy, Date>(new DatePickerCell(DateTimeFormat
                        .getFormat(PredefinedFormat.DATE_TIME_FULL))) {

                    @Override
                    public Date getValue(OrderIntervalProxy object) {
                        return object.getEnd();
                    }
                }, "Vége");

        Column<OrderIntervalProxy, String> countColumn = new Column<OrderIntervalProxy, String>(
                new EditTextCell()) {

            @Override
            public String getValue(OrderIntervalProxy object) {
                return object.getCount().toString();
            }

        };
        countColumn.setFieldUpdater(new FieldUpdater<OrderIntervalProxy, String>() {

            @Override
            public void update(int index, OrderIntervalProxy object, String value) {
                GWT.log("Old: " + object.getCount() + " New: " + value);
                object.setCount(Integer.parseInt(value));
            }
        });
        table.addColumn(countColumn, "Darab");
    }
}
