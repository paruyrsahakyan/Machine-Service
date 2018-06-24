package group.service.iko.service;

import group.service.iko.entityDao.MachineDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import group.service.iko.entities.Machine;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MachineService {
    private Session session;

    @Autowired
    private MachineDAO machineDAO;

    public MachineService() {

    }

    public List<Machine> getAllMachines() {

        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Machine";
        List<Machine> machineList = (List<Machine>) session.createQuery(hql).list();
        session.flush();
        session.close();
        return machineList;
    }

    public Machine getMachineById(int machineId) {

        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Machine where id= :machineId";
        Query query = session.createQuery(hql);
        query.setParameter("machineId", machineId);
        Machine machine = (Machine) query.uniqueResult();
        session.close();
        return machine;
    }

    public void saveMachine(Machine machine) {
        machineDAO.addMachine(machine);
    }

    public Machine getMachineByModelAndSerialNumber(String model, String serialNumber) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Machine where model='" +
                model + "' and serialNumber = '" + serialNumber + "'";
        Query query = session.createQuery(hql);
        Machine machine = (Machine) query.uniqueResult();
        session.close();
        return machine;
    }

    public void updateMachine(Machine machine) {
        machineDAO.updateMachine(machine);
    }

    public Machine getLastRecord() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Machine" +
                " order by id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        return (Machine) query.uniqueResult();

    }

    public void deleteMachineById(int machineId) {
        Machine machine = new Machine();
        machine.setId(machineId);
        machineDAO.deleteMachine(machine);
    }

    public int getCustomerIdByMachineId(int machineId) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "select customer.id from group.service.iko.entities.Machine " +
                "where id =" + machineId;
        Query query = session.createQuery(hql);
        int customerId = (Integer) query.uniqueResult();
        return customerId;

    }

    public List<Machine> getMachinesFiltered(String model, String serialNumber) {
        if(model.equals("")){
            return getMachinesFilteredBySerialNumber(model);
        }
        if (serialNumber.equals("")){
            return getMachinesFilteredByModel(model);
        }
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Machine " +
                "where model like :M  and serialNumber=:S";
        Query query = session.createQuery(hql);
        query.setParameter("M", "%"+model+"%");
        query.setParameter("S", serialNumber);
        List<Machine> machineList = (List<Machine>) query.list();
        session.close();
        return machineList;


    }

    public List<Machine> getMachinesFilteredByModel(String model) {
        if (model.equals("")){
            return getAllMachines();
        }
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Machine " +
                "where model like:M";
        Query query = session.createQuery(hql);
        query.setParameter("M", "%"+ model+"%");
        List<Machine> machineList = (List<Machine>) query.list();
        session.close();
        return machineList;
    }
    public List<Machine> getMachinesFilteredBySerialNumber(String serialNumber) {
        if(serialNumber.equals("")){
            return getAllMachines();
        }
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Machine " +
                "where serialNumber=:S";
        Query query = session.createQuery(hql);
        query.setParameter("S", serialNumber);
        List<Machine> machineList = (List<Machine>) query.list();
        session.close();
        return machineList;
    }
}
