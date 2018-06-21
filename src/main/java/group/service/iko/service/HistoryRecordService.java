package group.service.iko.service;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.db.HistoryRecordDAO;
import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.HistoryRecord;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.List;

@Service
public class HistoryRecordService {

    @Autowired
    private HistoryRecordDAO historyRecordDAO;
    private Session session;

    public HistoryRecordService() {

    }

    public void saveHistoryRecord(HistoryRecord historyRecord) {
        historyRecordDAO.saveRecord(historyRecord);

    }

    public HistoryRecord getLastRecord() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        Query query = session.createQuery("from group.service.iko.entities.HistoryRecord" +
                " order by id DESC");
        query.setMaxResults(1);
        HistoryRecord lastHistoryRecord = (HistoryRecord) query.uniqueResult();
        session.close();
        return lastHistoryRecord;
    }

    public HistoryRecord getHistoryRecordById(int id) {
        HistoryRecord historyRecord = historyRecordDAO.getRecordById(id);
        return  historyRecord;

    }


    public void updateHistoryRecord(HistoryRecord historyRecord) {

        historyRecordDAO.updateRecord(historyRecord);

    }

    public void deleteHistoryRecord(HistoryRecord historyRecord) {
        historyRecordDAO.deleteRecord(historyRecord);

    }

    public int getMachineIdByRecordId(int recordId) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "select machine.id from group.service.iko.entities.HistoryRecord where id = :recId";
        Query query = session.createQuery(hql);
        query.setParameter("recId", recordId);
        int machineId = (Integer) query.uniqueResult();
        session.close();
        return machineId;
    }

    public List<HistoryRecord> getAllRecordsOfMachine(int machineId) {

        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.HistoryRecord where machine.id = :MID";
        Query query = session.createQuery(hql);
        query.setParameter("MID", machineId);
        List<HistoryRecord> recordList = (List<HistoryRecord>) query.list();
        session.close();
        return recordList;
    }

    public List<HistoryRecord> getRecordsFilteredByDate(int machineId, String startDate, String endDate) {
        if (!startDate.equals("") && !endDate.equals("")) {
                   session = SessionFactoryImpl.getSessionFactory().openSession();
            GregorianCalendar start = CalendarAdapter.getGregCalendar(startDate);
            GregorianCalendar end = CalendarAdapter.getGregCalendar(endDate);
            String hql = "from group.service.iko.entities.HistoryRecord" +
                    " where machine.id=:MID " +
                    " and recordDate > :START" +
                    " and recordDate < :END";
            Query query = session.createQuery(hql);
            query.setParameter("MID", machineId);
            query.setParameter("START", start);
            query.setParameter("END", end);
            List<HistoryRecord> recordList = (List<HistoryRecord>) query.list();
            session.close();
            return recordList;
                    }
                    else  return getAllRecordsOfMachine(machineId);
        }
}
