package group.service.iko.service;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.db.DetailedLaborHourDAO;
import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;
import java.util.List;
@Service
public class DetailedLaborHourService {
    @Autowired
    private DetailedLaborHourDAO detailedLaborHourDAO;
    Session session;

    public void saveDetailedLaborHour(DetailedLaborHour detailedLaborHour) {
         detailedLaborHourDAO = new DetailedLaborHourDAO();
        detailedLaborHourDAO.saveDetailedLaborHour(detailedLaborHour);
    }

    public List<DetailedLaborHour> getDetailedLaborByRecordId(int recordId){
         detailedLaborHourDAO = new DetailedLaborHourDAO();
        return detailedLaborHourDAO.getDetailedLaborByRecordId(recordId);
    }

    public void deleteAllByHistoryRecordId(int historyRecordId) {
         session = SessionFactoryImpl.getSessionFactory().openSession();
        String sql = "delete from detailed_labor_hour where history_record_id =" + historyRecordId;
        SQLQuery sqlQuery = session.createSQLQuery(sql);
         sqlQuery.executeUpdate();
        session.close();

    }

    public List<DetailedLaborHour> getJobListByWorkerName(String workerName, String startDate, String endDate) {
        GregorianCalendar start = CalendarAdapter.getGregCalendar(startDate);
        GregorianCalendar end = CalendarAdapter.getGregCalendar(endDate);
         session = SessionFactoryImpl.getSessionFactory().openSession();
        String hql = "from group.service.iko.entities.DetailedLaborHour where " +
                "workerName = :name" +
                " and historyRecord.recordDate > :startD"+
                " and historyRecord.recordDate < :endD ";
          Query query = session.createQuery(hql);
          query.setParameter("name", workerName);
        query.setParameter("startD", start);
        query.setParameter("endD", end);
         List<DetailedLaborHour> laborHourList = (List<DetailedLaborHour>) query.list();
          session.close();
          return laborHourList;
    }
}


