package group.service.iko.service;

import group.service.iko.db.HistoryRecordHibernateDAO;
import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class HistoryRecordService {
    private HistoryRecordHibernateDAO historyRecordHibernateDAO;
    private Session session;

    public HistoryRecordService(){
        historyRecordHibernateDAO = new HistoryRecordHibernateDAO();
    }

public void saveHistoryRecord(HistoryRecord historyRecord){
        historyRecordHibernateDAO.saveRecord(historyRecord);

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
    public HistoryRecord getHistoryRecordById(int id){

    return historyRecordHibernateDAO.getRecordById(id);
    }
    public  void updateHistoryRecord(HistoryRecord historyRecord){

        historyRecordHibernateDAO.updateRecord(historyRecord);

    }
    public void deleteHistoryRecord(HistoryRecord historyRecord) {
        historyRecordHibernateDAO.deleteRecord(historyRecord);

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
}
