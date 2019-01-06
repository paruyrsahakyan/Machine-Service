package group.service.iko.entityDao;

import group.service.iko.entities.ServiceMachine;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class ServiceMachineDAO {
    private Session session;

    public ServiceMachine getServiceMachineById(int id){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.ServiceMachine where id = :ID";
        Query query = session.createQuery(hql);
        query.setParameter("ID", id);
        ServiceMachine serviceMachine = (ServiceMachine) query.uniqueResult();
        session.close();
        return serviceMachine;

        }
    public void saveServiceMachine(ServiceMachine serviceMachine){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(serviceMachine);
        session.flush();
        session.close();
            }
    public void updateServiceMachine(ServiceMachine serviceMachine){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.update(serviceMachine);
        session.flush();
        session.close();
            }
    public void deleteServiceMachine(ServiceMachine serviceMachine){
        session= SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(serviceMachine);
        session.flush();
        session.close();
            }

}
