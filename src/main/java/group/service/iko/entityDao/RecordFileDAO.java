package group.service.iko.entityDao;

import group.service.iko.entities.RecordFile;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

@Service
public class RecordFileDAO {
    private Session session;



    public void saveRecordFile(RecordFile recordFile) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(recordFile);
        session.flush();
        session.close();

    }
    public void deleteRecord(RecordFile recordFile){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.delete(recordFile);
        session.flush();
        session.close();
    }
   public  RecordFile getRecordFileById(int recorFileId){
        String hql="From group.service.iko.entities.RecordFile where id = "+recorFileId;
        session= SessionFactoryImpl.getSessionFactory().openSession();
        Query query = session.createQuery(hql);
         RecordFile recordFile = (RecordFile) query.uniqueResult();
          session.close();
       return recordFile;
         }

}
