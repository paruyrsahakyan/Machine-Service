package group.service.iko.service;

import group.service.iko.entities.Machine;
import group.service.iko.entities.PriceForCustomer;
import group.service.iko.entityDao.EntityDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PriceForCustomerService {
    @Autowired
    private EntityDAO<PriceForCustomer> entityDAO;
    private Session session;

    public PriceForCustomerService() {
    }

    public void savePriceForCustomer(PriceForCustomer priceForCustomer) {
        entityDAO.saveEntity(priceForCustomer);
    }

    public void updatePriceForCustomer(PriceForCustomer priceForCustomer) {
        entityDAO.updateEntity(priceForCustomer);
    }

    public void deletePriceForCustomer(PriceForCustomer priceForCustomer) {

        entityDAO.deleteEntity(priceForCustomer);

    }

    public PriceForCustomer getPriceForCustomerById(int id) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.PriceForCustomer WHERE id=" + id;
        Query query = session.createQuery(hql);
        PriceForCustomer t = (PriceForCustomer) query.uniqueResult();
        session.flush();
        session.close();
        return t;
    }

    public List<PriceForCustomer> getAllPriceForCustomer() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.PriceForCustomer";
        Query query = session.createQuery(hql);
        List<PriceForCustomer> EntityList = (List<PriceForCustomer>) query.list();
        session.flush();
        session.close();
        return EntityList;
    }

    public List<PriceForCustomer> getPriceForCustomerByArticle(String article) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.PriceForCustomer where article= :article";
        Query query = session.createQuery(hql);
        query.setParameter("article", article);
        List<PriceForCustomer> pricesByArticleForAllCustomers = (List<PriceForCustomer>) query.list();
        session.flush();
        session.close();
        return pricesByArticleForAllCustomers;

    }

    public List<String> getArticlesSet() {

        List<String> articleList = new ArrayList<>();
        List<PriceForCustomer> listOfPriceForCustomer =getAllPriceForCustomer();
        for (PriceForCustomer priceForCustomer : listOfPriceForCustomer) {
            articleList.add(priceForCustomer.getArticle());
        }
        articleList.stream().sorted().collect(Collectors.toSet());
        return articleList;
    }

}

