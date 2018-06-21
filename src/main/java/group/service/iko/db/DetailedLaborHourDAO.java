package group.service.iko.db;

import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetailedLaborHourDAO {
    private Session session;
    public void saveDetailedLaborHour(DetailedLaborHour detailedLaborHour){
        session = SessionFactoryImpl.getSessionFactory().openSession();
        session.save(detailedLaborHour);
        session.flush();
        session.close();
    }
    public List<DetailedLaborHour> getDetailedLaborByRecordId(int recordId) {
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.DetailedLaborHour laborHour " +
                " where laborHour.historyRecord.id=" +recordId ;
        Query query = session.createQuery(hql);
        List<DetailedLaborHour> detailedLaborHourList = ( List<DetailedLaborHour>) query.list();
        session.close();
        return detailedLaborHourList;
    }

}
