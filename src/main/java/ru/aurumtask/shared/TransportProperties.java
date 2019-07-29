package ru.aurumtask.shared;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface TransportProperties extends PropertyAccess<TransportEntity> {
    @Path("Idtransport")
    ModelKeyProvider<TransportEntity> key();

    ValueProvider<TransportEntity, Integer> Idtransport();
    ValueProvider<TransportEntity, String> stateNumber();
    ValueProvider<TransportEntity, String> Uin();
    ValueProvider<TransportEntity, String> Parkname();

}
