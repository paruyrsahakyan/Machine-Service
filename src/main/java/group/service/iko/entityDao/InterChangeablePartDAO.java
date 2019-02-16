package group.service.iko.entityDao;

import group.service.iko.entities.InterChangeablePart;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class InterChangeablePartDAO {

    private Session session;

    public void saveInterChangeablePart(InterChangeablePart interChangeablePart){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(interChangeablePart);
        session.flush();
        session.close();
    }

    public  void deleteInterChangeablePart (InterChangeablePart interChangeablePart){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(interChangeablePart);
        session.flush();
        session.close();
    }
    public  void updateInterChangeablePart (InterChangeablePart interChangeablePart){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.update(interChangeablePart);
        session.flush();
        session.close();
    }

}
