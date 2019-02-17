package group.service.iko.service;

import group.service.iko.entities.InterChangeablePart;
import group.service.iko.entityDao.InterChangeablePartDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public List<InterChangeablePart> getApplicablePartsByInterChangeablePart(InterChangeablePart interChangeablePart) {
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
         session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.InterChangeablePart";
        Query query = session.createQuery(hql);
        List<InterChangeablePart> interChangeablePartList = (List<InterChangeablePart>) query.list();
        session.flush();
        session.close();
        return  interChangeablePartList;
        }

    public  List<InterChangeablePart> getInterChangeableGroupParts(String basicPartNumber) {
     session =SessionFactoryImpl.getSessionFactory().openSession();
     String hql = "from group.service.iko.entities.InterChangeablePart where basicPartNumber = '"+
             basicPartNumber + "'";
     Query query = session.createQuery(hql);
     List<InterChangeablePart> interChangeableGroupParts = (List<InterChangeablePart>) query.list();
     session.flush();
     session.close();
     return  interChangeableGroupParts;

    }

    public InterChangeablePart getInterChangeablePartById(int id) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.InterChangeablePart where id= :partId";
        Query query = session.createQuery(hql);
        query.setParameter("partId", id);
        InterChangeablePart interChangeablePart  = (InterChangeablePart) query.uniqueResult();
        session.close();
        return interChangeablePart;

    }

    public void deleteInterchangeableGroup(String basicPartNumber) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        Query query = session.createQuery("delete group.service.iko.entities.InterChangeablePart where basicPartNumber = :partNumber");
        query.setParameter("partNumber", basicPartNumber);
        query.executeUpdate();
        session.flush();
        session.close();
            }

    public InterChangeablePart getInterChangeablePartByPartNumber(String partNumber) {
                    session =SessionFactoryImpl.getSessionFactory().openSession();
              String hql = "from group.service.iko.entities.InterChangeablePart where partNumber = '"+
                    partNumber + "'";
            Query query = session.createQuery(hql);
             InterChangeablePart interChangeablePart = (InterChangeablePart) query.uniqueResult();
            session.flush();
            session.close();
            return  interChangeablePart;
        }

        public List<InterChangeablePart> getApplicablePartsListByPartNumber (String partNumber) {
         if (getInterChangeableGroupParts(partNumber)!=null){
             return getInterChangeableGroupParts(partNumber);
            }
        InterChangeablePart interChangeablePart = getInterChangeablePartByPartNumber(partNumber);
        if (interChangeablePart == null) {
            return getApplicablePartsByInterChangeablePart(interChangeablePart);
        }
        return null;
    }
}
