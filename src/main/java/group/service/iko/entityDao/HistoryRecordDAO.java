package group.service.iko.entityDao;

import group.service.iko.entities.HistoryRecord;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryRecordDAO {

    private Session session;


    public HistoryRecordDAO()
    {

    }

    public void saveRecord(HistoryRecord record) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(record);
        session.flush();
        session.close();
    }

    public void updateRecord(HistoryRecord record) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.update(record);
        session.flush();
        session.close();
            }
    public void deleteRecord(HistoryRecord record){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(record);
        session.flush();
        session.close();
    }
    public HistoryRecord getRecordById(int id) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.HistoryRecord where id =" + id;
        Query query = session.createQuery(hql);
        HistoryRecord historyRecord = (HistoryRecord) query.uniqueResult();
        session.close();
        return  historyRecord;
    }
    public List<HistoryRecord> getAllRecords(){
        session= SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.HistoryRecord";
        Query query = session.createQuery(hql);
        List<HistoryRecord> historyRecordList =  (List<HistoryRecord>) query.list();
        session.close();
        return historyRecordList;
            }



}
