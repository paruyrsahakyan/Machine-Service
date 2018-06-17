package group.service.iko.service;

import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    Session session;



        public Role getRole(int id) {
           session= SessionFactoryImpl.getSessionFactory().openSession();
            String hql = "from group.service.iko.entities.Role where id = :i";
            Query query = session.createQuery(hql);
            query.setParameter("i", id);
            Role role = (Role) query.uniqueResult();
            session.close();
            return  role;
        }

    }

