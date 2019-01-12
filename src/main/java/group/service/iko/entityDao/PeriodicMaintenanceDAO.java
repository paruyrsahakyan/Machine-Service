package group.service.iko.entityDao;

import group.service.iko.entities.PeriodicMaintenance;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class PeriodicMaintenanceDAO {

    Session session;

    public void savePeriodicMaintenance(PeriodicMaintenance periodicMaintenance) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(periodicMaintenance);
        session.flush();
        session.close();
    }

    public void updatePeriodicMaintenance(PeriodicMaintenance periodicMaintenance) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.update(periodicMaintenance);
        session.flush();
        session.close();
    }

    public void deletePeriodicMaintenance(PeriodicMaintenance periodicMaintenance) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(periodicMaintenance);
        session.flush();
        session.close();
    }

    public PeriodicMaintenance getPeriodicMaintenanceById(int id) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.PeriodicMaintenance WHERE id=" + id;
        Query query = session.createQuery(hql);
        PeriodicMaintenance periodicMaintenance = (PeriodicMaintenance) query.uniqueResult();
        session.flush();
        session.close();
        return periodicMaintenance;
    }
}
