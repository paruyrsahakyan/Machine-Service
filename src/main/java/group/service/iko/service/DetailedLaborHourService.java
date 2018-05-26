package group.service.iko.service;

import group.service.iko.db.DetailedLaborHourDAO;
import group.service.iko.db.SessionFactoryImpl;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
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
}


