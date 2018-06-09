package group.service.iko.service;

import group.service.iko.db.RecordFileDAO;
import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.RecordFile;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.List;

public class RecordFileService {
    private RecordFileDAO recordFileDAO;
    Session session;

    public RecordFileService(){
        recordFileDAO = new RecordFileDAO();
    }

    public void saveFile(RecordFile recordFile){
            recordFileDAO.saveRecordFile(recordFile);
    }

    public List<RecordFile> getFilesByRecordId(int historyRecordId) {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.RecordFile where historyRecord.id =" +historyRecordId;
        Query query = session.createQuery(hql);
        List<RecordFile> recordFileList =  (List<RecordFile>) query.list();
        session.close();
        return  recordFileList;

    }

    public RecordFile getRecordFileById(int recodFileId){
        return recordFileDAO.getRecorFilById(recodFileId);

    }
    public void deleteFileById(int fileId){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "delete from group.service.iko.entities.RecordFile where id="+fileId;
        Query query = session.createQuery(hql);
        query.executeUpdate();
        session.close();
    }

    public int getNextId() {
        session = SessionFactoryImpl.getSessionFactory().openSession();
        String sql = "SELECT MAX(id) FROM iko.record_file";
        Query query = session.createSQLQuery(sql);
              Integer id= (Integer) query.uniqueResult();
              if(id==null) id = 0;
        session.close();
        return id+1;


    }
}
