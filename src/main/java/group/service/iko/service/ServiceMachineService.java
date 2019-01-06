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
    private List<ServiceMachine> allServiceMachines = null;
    private Session session;

    public void saveServiceMachine(ServiceMachine serviceMachine) {
        serviceMachineDAO.saveServiceMachine(serviceMachine);
    }

    public void updateServiceMachine(ServiceMachine serviceMachine) {
        serviceMachineDAO.updateServiceMachine(serviceMachine);
    }

    public void deleteServiceMachine(ServiceMachine serviceMachine) {
        serviceMachineDAO.deleteServiceMachine(serviceMachine);
    }

    public void getServiceMachineById(int id) {
        serviceMachineDAO.getServiceMachineById(id);
    }

    public  List<ServiceMachine> getAllServiceMachines() {
        if (allServiceMachines == null) {
            session = SessionFactoryImpl.getSessionFactory().openSession();
            String hql = "from group.service.iko.entities.ServiceMachine";
            Query query = session.createQuery(hql);
            List<ServiceMachine> all = (List<ServiceMachine>) query.list();
            session.close();
            allServiceMachines = all;
        }
        return allServiceMachines;
    }
}