package group.service.iko.service;

import group.service.iko.entities.*;
import group.service.iko.entityDao.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class OrderService {
    
    
    @Autowired
    private  EntityDAO<Order> entityDAO;

    public OrderService(EntityDAO<Order> entityDAO) {
        this.entityDAO = entityDAO;
    }

    public Order getOrderById(int id) {
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.order WHERE id=" + id;
        Query query = session.createQuery(hql);
        Order order = (Order) query.uniqueResult();
        session.flush();
        session.close();
        return order;
    }
    public void saveOrder(Order order){
        entityDAO.saveEntity(order);

    }

    public void upDateOrder(Order order){
        entityDAO.updateEntity(order);
    }

    public  void  deleteOrder(Order order){
        entityDAO.deleteEntity(order);
    }
}
