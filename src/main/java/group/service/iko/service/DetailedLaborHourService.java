package group.service.iko.service;

import group.service.iko.db.DetailedLaborHourDAO;
import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.List;

public class DetailedLaborHourService {

    public void saveDetailedLaborHour(DetailedLaborHour detailedLaborHour) {
        DetailedLaborHourDAO detailedLaborHourDAO = new DetailedLaborHourDAO();
        detailedLaborHourDAO.saveDetailedLaborHour(detailedLaborHour);
    }

    public List<DetailedLaborHour> getDetailedLaborByRecordId(int recordId){
        DetailedLaborHourDAO detailedLaborDAO = new DetailedLaborHourDAO();
        return detailedLaborDAO.getDetailedLaborByRecordId(recordId);
    }

    public void deleteAllByHistoryRecordId(int historyRecordId) {
        Session session = SessionFactoryImpl.getSessionFactory().openSession();
        String sql = "delete from detailed_labor_hour where history_record_id =" + historyRecordId;
        SQLQuery sqlQuery = session.createSQLQuery(sql);
         sqlQuery.executeUpdate();
        session.close();

    }
}


