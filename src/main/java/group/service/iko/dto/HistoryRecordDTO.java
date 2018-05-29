package group.service.iko.dto;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import group.service.iko.entities.RecordFile;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class HistoryRecordDTO {

    private  int id;
    private  String title;
    private String recordDate;
    private int SMR;
    private String recordInformation;
    private String machine;
    private String otherInfo;
    private int laborHour;

    public HistoryRecordDTO(HistoryRecord historyRecord) {
        id =historyRecord.getId();
        title = historyRecord.getTitle();
        recordDate = CalendarAdapter.getStringFormat(historyRecord.getRecordDate());
        SMR = historyRecord.getSMR();
        recordInformation = historyRecord.getRecordInformation();
        Machine machineOfRecord = historyRecord.getMachine();
        machine = machineOfRecord.getModel() +"; sn " +machineOfRecord.getSerialNumber();
        otherInfo = historyRecord.getOtherInfo();
        laborHour = historyRecord.getLaborHour();
            }

            public static List<HistoryRecordDTO> transformIntoDTO(List<HistoryRecord> recordList){
        List<HistoryRecordDTO > recordDTOList = new ArrayList<HistoryRecordDTO>();
        for(HistoryRecord historyRecord : recordList){
            recordDTOList.add(new HistoryRecordDTO(historyRecord));
        }
        return recordDTOList;
            }


    @Override
    public String toString() {
        return "HistoryRecordDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", recordDate='" + recordDate + '\'' +
                ", SMR=" + SMR +
                ", recordInformation='" + recordInformation + '\'' +
                ", machine='" + machine + '\'' +
                ", otherInfo='" + otherInfo + '\'' +
                ", laborHour=" + laborHour +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public int getSMR() {
        return SMR;
    }

    public void setSMR(int SMR) {
        this.SMR = SMR;
    }

    public String getRecordInformation() {
        return recordInformation;
    }

    public void setRecordInformation(String recordInformation) {
        this.recordInformation = recordInformation;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public int getLaborHour() {
        return laborHour;
    }

    public void setLaborHour(int laborHour) {
        this.laborHour = laborHour;
    }
}
