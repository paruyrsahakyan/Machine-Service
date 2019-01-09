package group.service.iko.entityDao;

import group.service.iko.entities.WorkOrder;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderDAO {
    Session session;

    public void saveWorkOrder(WorkOrder workOrder){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(workOrder);
        session.flush();
        session.close();
    }

    public void updateWorkOrder(WorkOrder workOrder){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.update(workOrder);
        session.flush();
        session.close();
    }

    public void deleteWorkOrder(WorkOrder workOrder){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(workOrder);
        session.flush();
        session.close();
    }

    public WorkOrder getWorkOrderById(int id) {
        session= SessionFactoryImpl.getSessionFactory().openSession();
        String hql ="From group.service.iko.entities.WorkOrder where id = " + id;
        Query query = session.createQuery(hql);
        WorkOrder workOrder = (WorkOrder) query.uniqueResult();
        session.close();
        return  workOrder;
        }

}
