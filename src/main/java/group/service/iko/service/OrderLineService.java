package group.service.iko.service;

import group.service.iko.entities.*;
import group.service.iko.entityDao.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class OrderLineService {
    
    @Autowired
    private EntityDAO<OrderLine> entityDAO;


    public OrderLineService() {
    }

    public OrderLine getOrderLineById(int id) {
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.OrderLine WHERE id=" + id;
        Query query = session.createQuery(hql);
        OrderLine orderLine = (OrderLine) query.uniqueResult();
        session.flush();
        session.close();
        return orderLine;
    }

    public void saveOfferLine(OrderLine orderLine){
        entityDAO.saveEntity(orderLine);

    }

    public void upDateOfferLine(OrderLine orderLine){
        entityDAO.updateEntity(orderLine);
    }

    public  void  deleteOfferLine(OrderLine orderLine){
        entityDAO.deleteEntity(orderLine);
    }



}

