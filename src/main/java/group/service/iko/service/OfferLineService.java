package group.service.iko.service;

import group.service.iko.entities.*;
import group.service.iko.entityDao.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;


@Service
public class OfferLineService {

    private Session session;
    @Autowired
    private EntityDAO<OfferLine> entityDAO;

    public OfferLineService() {
    }

    public OfferLine getOfferLineById(int id) {
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.OfferLine WHERE id=" + id;
        Query query = session.createQuery(hql);
        OfferLine offerLine = (OfferLine) query.uniqueResult();
        session.flush();
        session.close();
        return offerLine;

    }

     public  void saveOfferLine(OfferLine offerLine){
        entityDAO.saveEntity(offerLine);
     }
    public void updateOfferLine(OfferLine offerLine) {
        entityDAO.updateEntity(offerLine);
    }

    public void deleteOfferLine(OfferLine offerLine) {

        entityDAO.deleteEntity(offerLine);

    }


}


