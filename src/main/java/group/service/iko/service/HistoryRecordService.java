package group.service.iko.service;

import group.service.iko.db.HistoryRecordHibernateDAO;
import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import org.hibernate.Query;
import org.hibernate.Session;

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


}
