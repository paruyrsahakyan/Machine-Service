package group.service.iko.service;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.dto.HistoryRecordDTO;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entityDao.MachineDAO;
import group.service.iko.entityDao.SessionFactoryImpl;
import group.service.iko.entities.Machine;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

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

    public Machine getLastMachine() {
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
    public HistoryRecordDTO getLastInfoOfMachine(Machine machine){
        HistoryRecordDTO historyRecord = new HistoryRecordDTO();
                  if (machine.getHistoryRecordList().size()>0) {
                             session = SessionFactoryImpl.getSessionFactory().openSession();
                      String sql;
                                   if (machine.getHistoryRecordList().size()==1){

                                 sql = "SELECT * FROM iko.history_record where machine_id = " + machine.getId();
                             }
                             else {
                                 sql = "SELECT * FROM iko.history_record where machine_id = " + machine.getId() +
                                         " and recordDate =(select max(recordDate) from iko.history_record where machine_id="+
                                         machine.getId()+" );";
                             }
                Query query = session.createSQLQuery(sql);
                List<Object[]> resultList =  query.list();
                Object[] result = resultList.get(0);
                historyRecord.setId(Integer.parseInt(result[0].toString()));
                historyRecord.setTitle(result[6].toString());
                historyRecord.setSMR(Integer.parseInt(result[1].toString()));
                historyRecord.setRecordDate(result[4].toString().substring(0,10));
                  session.close();
//                String hql = "select max(recordDate) from group.service.iko.entities.HistoryRecord  where machine.id=:mID ";
//                Query query = session.createQuery(hql);
//                 query.setParameter("mID", machine.getId());
//                GregorianCalendar gregorianCalendar = (GregorianCalendar) query.uniqueResult();
//                String hql2 = "from group.service.iko.entities.HistoryRecord where machine.id = :M and recordDate = :date";
//                Query query1 = session.createQuery(hql2);
//                query1.setParameter("M", machine.getId());
//                query1.setParameter("date", gregorianCalendar );
//                HistoryRecord historyRecord1 = (HistoryRecord) query1.uniqueResult();
//                session.close();
            }
        return  historyRecord;
    }

}
