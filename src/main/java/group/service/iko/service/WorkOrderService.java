package group.service.iko.service;

import group.service.iko.entities.WorkOrder;
import group.service.iko.entityDao.WorkOrderDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderService {
    @Autowired
    private WorkOrderDAO workOrderDAO;

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

}
