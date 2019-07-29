package ru.aurumtask.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.aurumtask.shared.TransportEntity;

import java.util.List;

public interface MainRpcServiceAsync {
    void getAllTransport(AsyncCallback<List<TransportEntity>> async);

    void saveTransport(TransportEntity item, AsyncCallback<TransportEntity> async);
    void deleteTransport(TransportEntity item, AsyncCallback<TransportEntity> async);
    void updateTransport(TransportEntity item, AsyncCallback<TransportEntity> async);

}