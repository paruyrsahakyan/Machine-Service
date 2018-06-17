package group.service.iko.service;

import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.User;
import org.hibernate.Query;
import org.hibernate.Session;


public class UserService {
    Session session;


    public  User getUser(String login) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.User where login = :login";
        Query query = session.createQuery(hql);
        query.setParameter("login", login);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

}
