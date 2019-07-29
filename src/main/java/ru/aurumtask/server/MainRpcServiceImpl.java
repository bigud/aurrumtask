package ru.aurumtask.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.aurumtask.client.MainRpcService;
import ru.aurumtask.shared.TransportEntity;
import java.util.ArrayList;
import java.util.List;

public class MainRpcServiceImpl extends RemoteServiceServlet implements MainRpcService {

    private SessionFactory sessionFactory;
    private Session session;
    private List itemList = new ArrayList<>();

    public MainRpcServiceImpl() {
        sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public List<TransportEntity> getAllTransport() {
        Query query =  session.createQuery("FROM TransportEntity");
        itemList = query.list();
        return itemList;
    }

    @Override
    public TransportEntity saveTransport(TransportEntity item) {
        session.save(item);
        return null;
    }
    @Override
    public TransportEntity deleteTransport(TransportEntity item) {
        session.delete(item);
        return null;
    }
    @Override
    public TransportEntity updateTransport(TransportEntity item) {
        session.update(item);
        return null;
    }
}
