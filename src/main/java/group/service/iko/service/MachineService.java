package group.service.iko.service;

import group.service.iko.db.MachineHibernateDAO;
import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.Machine;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MachineService {

    private MachineHibernateDAO machineHibernateDAO;

    public MachineService(){
        machineHibernateDAO = new MachineHibernateDAO();

    }

    public List<Machine> getAllMachines() {

        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Machine";
        List<Machine> machineList = (List<Machine>) session.createQuery(hql).list();
        session.flush();
        session.close();
        return machineList;
    }

    public Machine getMachineById(int machineId) {

        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Machine where id="+machineId;
        Machine machine = (Machine) session.createQuery(hql).uniqueResult();
        session.close();
        return machine;
    }

    public void saveMachine(Machine machine) {
        machineHibernateDAO.addMachine(machine);
    }

    public Machine getMachineByModelAndSerialNumber(String model, String serialNumber) {
        Session session =SessionFactoryImpl.getSessionFactory().openSession();
        String hql ="from group.service.iko.entities.Machine where model='"+
                model+"' and serialNumber = '"+ serialNumber+"'";
        Query query = session.createQuery(hql);
        Machine machine = (Machine) query.uniqueResult();
        session.close();
        return  machine;
    }

    public void updateMachine(Machine machine) {
        machineHibernateDAO.updateMachine(machine);
    }

    public Machine getLastRecord() {
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.Machine"+
                " order by id DESC";
        Query query = session.createQuery(hql);
        query.setMaxResults(1);
        return (Machine) query.uniqueResult();

    }
}
