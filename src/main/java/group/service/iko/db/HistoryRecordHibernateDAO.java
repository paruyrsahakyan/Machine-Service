package group.service.iko.db;

import group.service.iko.entities.HistoryRecord;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class HistoryRecordHibernateDAO {


    private SessionFactory sessionFactory;
    private Session session;


    public  HistoryRecordHibernateDAO()
    {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void saveRecord(HistoryRecord record) {
        session = sessionFactory.openSession();
        session.save(record);
        session.flush();
        session.close();
    }

    public void updateRecord(HistoryRecord record) {
        session = sessionFactory.openSession();
        session.update(record);
        session.flush();
        session.close();
            }
    public void deleteRecord(HistoryRecord record){
        session = sessionFactory.openSession();
        session.delete(record);
        session.flush();
        session.close();
    }
    public HistoryRecord getRecordById(int id) {
        session = sessionFactory.openSession();
        String hql = "from group.service.iko.entities.HistoryRecord where id =" + id;
        Query query = session.createQuery(hql);
        HistoryRecord historyRecord = (HistoryRecord) query.uniqueResult();
        session.close();
        return  historyRecord;
    }
    public List<HistoryRecord> getAllRecords(){
        session= sessionFactory.openSession();
        String hql = "from group.service.iko.entities.HistoryRecord";
        Query query = session.createQuery(hql);
        List<HistoryRecord> historyRecordList =  (List<HistoryRecord>) query.list();
        session.close();
        return historyRecordList;
            }



}
