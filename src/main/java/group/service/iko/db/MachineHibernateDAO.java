package group.service.iko.db;

import group.service.iko.entities.Machine;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


public class MachineHibernateDAO {

    private Session session;

    public void addMachine(Machine machine) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(machine);
        session.flush();
        session.close();
    }
    public  void deleteMachine(Machine machine){
       session = SessionFactoryImpl.getSessionFactory().openSession();
       session.delete(machine);
        session.flush();
             session.close();

    }
    public  void updateMachine(Machine machine){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.update(machine);
        session.flush();
        session.close();
    }

    public List<Machine> getMachinesByCustomer(String customer) {
        session =SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "FROM group.service.iko.entities.Machine WHERE customer='"
                + customer+"'";
        Query query =session.createQuery(hql);
        List<Machine> machineList = query.list();
        session.close();
        return (machineList);

    }

}

