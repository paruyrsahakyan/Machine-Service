package group.service.iko.db;

import group.service.iko.entities.RecordFile;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RecordFileHibernateDAO {
    private SessionFactory sessionFactory;
    private Session session;

    public RecordFileHibernateDAO() {
        this.sessionFactory = SessionFactoryImpl.getSessionFactory();
    }

    public void saveRecordFile(RecordFile recordFile) {
        session = sessionFactory.openSession();
        session.save(recordFile);
        session.flush();
        session.close();

    }
    public void deleteRecord(RecordFile recordFile){
        session = sessionFactory.openSession();
        session.delete(recordFile);
        session.flush();
        session.close();
    }
   public  RecordFile getRecorFilById(int recorFileId){
        String hql="From RecordFile where id = "+recorFileId;
        session= sessionFactory.openSession();
        return (RecordFile) session.createQuery(hql).uniqueResult();
         }

}
