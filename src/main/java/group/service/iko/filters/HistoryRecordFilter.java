package group.service.iko.filters;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.HistoryRecord;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

public class HistoryRecordFilter {


    public static List<HistoryRecord> filterRecordsByDate(List<HistoryRecord> recordList,
                                                          GregorianCalendar startDate,
                                                          GregorianCalendar endDate) {

        List<HistoryRecord> filteredRecordList = new ArrayList<HistoryRecord>();
        /*GregorianCalendar recordDate;
        for (HistoryRecord record : recordList) {
            recordDate = record.getRecordDate();
            if (recordDate.after(startDate) && recordDate.before(endDate)) {
                filteredRecordList.add(record);
            }
        }*/

        return recordList.stream().filter(
                historyRecord -> historyRecord.getRecordDate().after(startDate)
                        && historyRecord.getRecordDate().before(endDate)).collect(Collectors.toList());

//        return filteredRecordList;
    }

    public static List<HistoryRecord> filterRecordsByDate(List<HistoryRecord> recordList,
                                                          String startDate,
                                                          String endDate) {
        GregorianCalendar startCalendar;
        GregorianCalendar endCalendar;

        if (startDate.equals("") && endDate.equals("")) {
            return recordList;
        } else {
            if (startDate.equals("")) {
                startDate = "2010-01-01";
            }
            startCalendar = CalendarAdapter.getGregCalendar(startDate);

            if (endDate.equals("")) {
                endCalendar = new GregorianCalendar();
            } else {
                endCalendar = CalendarAdapter.getGregCalendar(endDate);
            }
        }
       return filterRecordsByDate(recordList, startCalendar, endCalendar);

    }
}
