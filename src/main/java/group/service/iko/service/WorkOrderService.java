package group.service.iko.service;

import group.service.iko.entities.WorkOrder;
import group.service.iko.entityDao.SessionFactoryImpl;
import group.service.iko.entityDao.WorkOrderDAO;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderService {
    @Autowired
    private WorkOrderDAO workOrderDAO;
    Session session;

    public  void saveWorkOrder(WorkOrder workOrder){
        workOrderDAO.saveWorkOrder(workOrder);
    }
public void updateWorkOrder(WorkOrder workOrder){
        workOrderDAO.updateWorkOrder(workOrder);
}
public void deleteWorkOrder(WorkOrder workOrder){
        workOrderDAO.deleteWorkOrder(workOrder);
}
public  void  getWorkOrderById(int id){
        workOrderDAO.getWorkOrderById(id);
}

public WorkOrder getLastSavedWorkOrder() {
    session = SessionFactoryImpl.getSessionFactory().openSession();
    String hql = "from group.service.iko.entities.WorkOrder" +
            " order by id DESC";
    Query query = session.createQuery(hql);
    query.setMaxResults(1);
    WorkOrder workOrder = (WorkOrder) query.uniqueResult();
    session.flush();
    session.close();
    return workOrder;

}
}
