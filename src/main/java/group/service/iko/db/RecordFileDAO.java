package group.service.iko.db;

import group.service.iko.entities.RecordFile;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RecordFileDAO {
    private SessionFactory sessionFactory;
    private Session session;

    public RecordFileDAO() {
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
        String hql="From group.service.iko.entities.RecordFile where id = "+recorFileId;
        session= sessionFactory.openSession();
        Query query = session.createQuery(hql);
         RecordFile recordFile = (RecordFile) query.uniqueResult();
          session.close();
       return recordFile;
         }

}
