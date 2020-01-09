package group.service.iko.entityDao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityDAO<T> {

    private SessionFactory sessionFactory;
    private Session session;


    public void saveEntity(T t) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(t);
        session.flush();
        session.close();
    }

    public void updateEntity(T t) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.update(t);
        session.flush();
        session.close();
    }

    public void deleteEntity(T t) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(t);
        session.flush();
        session.close();
    }


}
