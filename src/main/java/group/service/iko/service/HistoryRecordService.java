package group.service.iko.service;

import group.service.iko.db.HistoryRecordHibernateDAO;
import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class HistoryRecordService {
    HistoryRecordHibernateDAO historyRecordHibernateDAO;
    public HistoryRecordService(){
        historyRecordHibernateDAO = new HistoryRecordHibernateDAO();
    }
public void saveHistoryRecord(HistoryRecord historyRecord){
        historyRecordHibernateDAO.saveRecord(historyRecord);

}

    public HistoryRecord getLastRecord() {
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        Query query = session.createQuery("from group.service.iko.entities.HistoryRecord" +
                " order by id DESC");
        query.setMaxResults(1);
        HistoryRecord lastHistoryRecord = (HistoryRecord) query.uniqueResult();
        session.close();
        return lastHistoryRecord;
    }
    public HistoryRecord getHistoryRecordById(int id){
    HistoryRecordHibernateDAO historyRecordHibernateDAO = new HistoryRecordHibernateDAO();
    return historyRecordHibernateDAO.getRecordById(id);
    }
    public  void updateHistoryRecord(HistoryRecord historyRecord){
        HistoryRecordHibernateDAO historyRecordHibernateDAO = new HistoryRecordHibernateDAO();
        historyRecordHibernateDAO.updateRecord(historyRecord);

    }

}
