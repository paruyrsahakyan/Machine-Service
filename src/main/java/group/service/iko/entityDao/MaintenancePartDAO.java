package group.service.iko.entityDao;

import group.service.iko.entities.MaintenancePart;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaintenancePartDAO {
private Session session;


public void saveMaintenancePart(MaintenancePart maintenancePart){
    session= SessionFactoryImpl.getSessionFactory().openSession();
    session.save(maintenancePart);
    session.flush();
    session.close();
}

public void updateMaintenancePart(MaintenancePart maintenancePart){
    session= SessionFactoryImpl.getSessionFactory().openSession();
    session.update(maintenancePart);
    session.flush();
    session.close();
}

    public void deleteMaintenancePart(MaintenancePart maintenancePart){
        session= SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(maintenancePart);
        session.flush();
        session.close();
}
public MaintenancePart getMeintenancePartById(int id){
    session= SessionFactoryImpl.getSessionFactory().openSession();
    String hql ="From group.service.iko.entities.MaintenancePart where id = " + id;
    Query query = session.createQuery(hql);
    MaintenancePart maintenancePart = (MaintenancePart) query.uniqueResult();
    session.close();
    return  maintenancePart;
    }

}
