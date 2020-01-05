package group.service.iko.entityDao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

           import org.hibernate.Query;
        import org.hibernate.Session;
        import org.hibernate.SessionFactory;
        import org.springframework.stereotype.Service;

        import java.util.List;
@Service
public class EntityDAO<T> {

    private SessionFactory sessionFactory;
    private Session session;
    public EntityDAO() {

    }

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

    public T getEntityById(int id) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.Entity WHERE id=" + id;
        Query query = session.createQuery(hql);
        T t = (T) query.uniqueResult();
        session.flush();
        session.close();
        return t;

    }

    public List<T> getAllEntitys() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Entity";
        Query query = session.createQuery(hql);
        List<T> EntityList= (List<T>) query.list();
        session.flush();
        session.close();
        return EntityList;
    }


}
