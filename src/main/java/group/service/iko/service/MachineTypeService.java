package group.service.iko.service;
import group.service.iko.entities.MachineType;
import group.service.iko.entityDao.MachineTypeDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MachineTypeService {

    @Autowired
    private MachineTypeDAO machineTypeDAO;
    private Session session;

    public void saveMachineType(MachineType machineType) {
        machineTypeDAO.saveMachineType(machineType);
    }

    public MachineType getMachineTypeById(int id) {
        return machineTypeDAO.getMachineTypeById(id);
    }

    public void updateMachineType(MachineType machineType) {
        machineTypeDAO.updateMachineType(machineType);
    }

    public void deleteMachineType(MachineType machineType) {
        machineTypeDAO.deleteMachineType(machineType);
    }

    public List<MachineType> getAllMachineTypes() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.MachineType";
        Query query = session.createQuery(hql);
        List<MachineType> machineTypeList = (List<MachineType>) query.list();
        session.flush();
        session.close();
        return machineTypeList;
    }



}
