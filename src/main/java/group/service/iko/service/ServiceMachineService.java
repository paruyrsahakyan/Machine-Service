package group.service.iko.service;

import group.service.iko.entities.ServiceMachine;
import group.service.iko.entityDao.ServiceMachineDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceMachineService {
    @Autowired
    private ServiceMachineDAO serviceMachineDAO;
    private Session session;
    private List<ServiceMachine> serviceMachineList=null;

    public void saveServiceMachine(ServiceMachine serviceMachine) {
        serviceMachineDAO.saveServiceMachine(serviceMachine);
        serviceMachineList=null;
    }

    public void updateServiceMachine(ServiceMachine serviceMachine) {
        serviceMachineDAO.updateServiceMachine(serviceMachine);
        serviceMachineList=null;
    }

    public void deleteServiceMachine(ServiceMachine serviceMachine) {
        serviceMachineDAO.deleteServiceMachine(serviceMachine);
        serviceMachineList=null;
    }

    public void getServiceMachineById(int id) {
        serviceMachineDAO.getServiceMachineById(id);
    }

    public  List<ServiceMachine> getAllServiceMachines() {
        if (serviceMachineList == null) {
            session = SessionFactoryImpl.getSessionFactory().openSession();
            String hql = "from group.service.iko.entities.ServiceMachine";
            Query query = session.createQuery(hql);
            List<ServiceMachine> all = (List<ServiceMachine>) query.list();
            session.close();
            serviceMachineList = all;
        }
        return serviceMachineList;
    }
}