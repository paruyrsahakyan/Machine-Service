package group.service.iko.service;

import group.service.iko.entities.*;
import group.service.iko.entityDao.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class OfferService {

    @Autowired
    private  EntityDAO<Offer> entityDAO;

    public OfferService() {
    }


    public Offer getOfferById(int id) {
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.Offer WHERE id=" + id;
        Query query = session.createQuery(hql);
        Offer offer = (Offer) query.uniqueResult();
        session.flush();
        session.close();
        return offer;
    }
    public void saveOffer(Offer offer){
        entityDAO.saveEntity(offer);

    }

    public void upDateOffer(Offer offer){
        entityDAO.updateEntity(offer);
    }

    public  void  deleteOffer(Offer offer){
        entityDAO.deleteEntity(offer);
    }

    public List<Offer> getCurrentOffers(){

        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.Offer WHERE condition=closed";
        Query query = session.createQuery(hql);
        List<Offer> offerList = (List<Offer>) query.list();
        session.flush();
        session.close();
        return  offerList;
    }

    }

