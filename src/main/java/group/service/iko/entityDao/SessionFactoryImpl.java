package group.service.iko.entityDao;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

@Service
public class SessionFactoryImpl {

    private static SessionFactory sessionFactory;

    public SessionFactoryImpl() {

    }

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            synchronized (SessionFactoryImpl.class) {

                sessionFactory = new Configuration().configure().buildSessionFactory();

            }

        }
        return sessionFactory;

    }
}
