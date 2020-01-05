package group.service.iko.service;

import group.service.iko.entities.PriceForCustomer;
import group.service.iko.entityDao.EntityDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        entityDAO.saveEntity(priceForCustomer);
    }

    public void deletePriceForCustomer(PriceForCustomer priceForCustomer) {
        entityDAO.deleteEntity(priceForCustomer);
    }

    public PriceForCustomer getPriceForCustomerById(int id) {
        return entityDAO.getEntityById(id);
    }

    public List<PriceForCustomer> getAllEntitys() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.priceForCustomer";
        Query query = session.createQuery(hql);
        List<PriceForCustomer> EntityList= (List<PriceForCustomer>) query.list();
        session.flush();
        session.close();
        return EntityList;
    }

    }
