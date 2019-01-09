package group.service.iko.entityDao;

import group.service.iko.entities.MachineType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class MachineTypeDAO {
Session session;

    public void saveMachineType(MachineType machineType){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(machineType);
        session.flush();
        session.close();
            }
    public MachineType getMachineTypeById(int recordFileId) {
        String hql = "From group.service.iko.entities.MachineType where id = " + recordFileId;
        session = SessionFactoryImpl.getSessionFactory().openSession();
        Query query = session.createQuery(hql);
        MachineType machineType = (MachineType) query.uniqueResult();
        session.close();
        return machineType;
    }
    public void updateMachineType(MachineType machineType){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.update(machineType);
        session.flush();
        session.close();
    }
    public void deleteMachineType(MachineType machineType){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(machineType);
        session.flush();
        session.close();
    }

}
