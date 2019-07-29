package ru.aurumtask.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.aurumtask.shared.TransportEntity;

import java.util.List;

@RemoteServiceRelativePath("gwtService")
public interface MainRpcService extends RemoteService {

    List<TransportEntity> getAllTransport();
    TransportEntity saveTransport(TransportEntity item);
    TransportEntity deleteTransport(TransportEntity item);
    TransportEntity updateTransport(TransportEntity item);

}