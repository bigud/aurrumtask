package ru.aurumtask.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
//import com.google.gwt.user.client.Window;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.FramedPanel;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.form.IntegerField;
import com.sencha.gxt.widget.core.client.grid.CellSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.grid.editing.GridEditing;
import com.sencha.gxt.widget.core.client.grid.editing.GridInlineEditing;
import ru.aurumtask.shared.TransportEntity;
import ru.aurumtask.shared.TransportProperties;

import java.util.ArrayList;
import java.util.List;

public class GWTModule implements EntryPoint {
    public void onModuleLoad() {
        final TransportProperties properties = GWT.create(TransportProperties.class);
        final List<ColumnConfig<TransportEntity, ?>> columns = new ArrayList<ColumnConfig<TransportEntity, ?>>();
        ColumnConfig<TransportEntity, Integer> colIdtransport = new ColumnConfig<TransportEntity, Integer>(properties.idtransport(), 55, "idtransport");
        ColumnConfig<TransportEntity, String> colstateNumber = new ColumnConfig<TransportEntity, String>(properties.stateNumber(), 150, "stateNumber");
        ColumnConfig<TransportEntity, String> colUin = new ColumnConfig<TransportEntity, String>(properties.uin(), 55, "uin");
        ColumnConfig<TransportEntity, String> colParkName = new ColumnConfig<TransportEntity, String>(properties.parkname(), 150, "parkname");
        columns.add(colIdtransport);
        columns.add(colstateNumber);
        columns.add(colUin);
        columns.add(colParkName);
        final ColumnModel<TransportEntity> columnModel = new ColumnModel<TransportEntity>(columns);
        MainRpcServiceAsync rpcService = GWT.create(MainRpcService.class);

        rpcService.getAllTransport(new AsyncCallback<List<TransportEntity>>() {
            @Override
            public void onFailure(Throwable caught) {
                MessageBox messageBox = new MessageBox("Ошибка получения списка транспорта");
                messageBox.show();
            }

            @Override
            public void onSuccess(List<TransportEntity> result) {
                final ListStore<TransportEntity> store = new ListStore<TransportEntity>(properties.key());
                store.addAll(result);
                // Initialize the Grid.
                final Grid<TransportEntity> grid = new Grid<TransportEntity>(store, columnModel);
                grid.getView().setAutoExpandColumn(colIdtransport);
                grid.setSelectionModel(new CellSelectionModel<TransportEntity>());
                grid.getColumnModel().getColumn(0).setHideable(false);

                // Setup the editors. Designate the input type per column.
                final GridEditing<TransportEntity> editing = new GridInlineEditing<TransportEntity>(grid);
                editing.addEditor(colstateNumber, new TextField());
                editing.addEditor(colUin, new TextField());
                editing.addEditor(colParkName, new TextField());
                editing.addEditor(colIdtransport, new IntegerField());

                VerticalLayoutContainer verticalLayoutContainer = new VerticalLayoutContainer();
                verticalLayoutContainer.add(grid, new VerticalLayoutContainer.VerticalLayoutData(1, 1));

                FramedPanel framedPanel = new FramedPanel();
                framedPanel.setHeading("Список транспортных средств");
                framedPanel.setPixelSize(640, 480);
                framedPanel.setHeight(480);
                framedPanel.add(verticalLayoutContainer);

                framedPanel.addButton(new TextButton("Add", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        TransportEntity item = new TransportEntity();
                        item.setIdtransport(result.size());
                        item.setStateNumber("RUS");
                        item.setParkname("");
                        item.setUin("1");

                        editing.cancelEditing();
                        store.add(0, item);

                        int row = store.indexOf(item);
                        editing.startEditing(new Grid.GridCell(row, 0));
                    }
                }));
                framedPanel.addButton(new TextButton("Reset", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        store.rejectChanges();
                    }
                }));
                framedPanel.addButton(new TextButton("Save", new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        store.commitChanges();
                    }
                }));
                RootPanel.get().add(framedPanel);
//                MessageBox messageBox = new MessageBox(result.get(0).getStateNumber(), store.get(0).getStateNumber());
//                messageBox.show();
            }
        });
        //End of onModuleLoad
    }
}
