package ru.aurumtask.shared;
import com.google.gwt.editor.client.Editor.Path;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.LabelProvider;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;

public interface TransportProperties extends PropertyAccess<TransportEntity> {
    @Path("attributeIdtransport")
    ModelKeyProvider<TransportEntity> key();

    ValueProvider<TransportEntity, Integer> attributeIdtransport();
    ValueProvider<TransportEntity, String> attributestateNumber();
    ValueProvider<TransportEntity, String> attributestateUin();
    ValueProvider<TransportEntity, String> attributestateParkname();

}
