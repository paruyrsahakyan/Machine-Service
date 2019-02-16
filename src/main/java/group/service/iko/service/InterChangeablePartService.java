package group.service.iko.service;

import group.service.iko.entities.InterChangeablePart;
import group.service.iko.entityDao.InterChangeablePartDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class InterChangeablePartService {
    @Autowired
    InterChangeablePartDAO interChangeablePartDAO;
    private Session session;

    public void saveInterChangeablePart(InterChangeablePart interChangeablePart){
    interChangeablePartDAO.saveInterChangeablePart(interChangeablePart);
    }
    public void updateInterChangeablePart(InterChangeablePart interChangeablePart){
        interChangeablePartDAO.updateInterChangeablePart(interChangeablePart);
            }

    public void  deleteInterchangeablePart(InterChangeablePart interChangeablePart){
        interChangeablePartDAO.deleteInterChangeablePart(interChangeablePart);
    }

    public List<InterChangeablePart> getApplicableParts(InterChangeablePart interChangeablePart) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.InterChangeablePart WHERE basicPart='"
                + interChangeablePart.getBasicPartNumber()+"'";
        Query query = session.createQuery(hql);
        List<InterChangeablePart> interChangeablePartList =  (List<InterChangeablePart>) query.list();
        session.flush();
        session.close();
        return  interChangeablePartList;
    }
    public List<InterChangeablePart> getAllInterChangeableParts(){
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.InterChangeablePart";
        Query query = session.createQuery(hql);
        List<InterChangeablePart> interChangeablePartList = (List<InterChangeablePart>) query.list();
        session.flush();
        session.close();
        return  interChangeablePartList;
        }
}
