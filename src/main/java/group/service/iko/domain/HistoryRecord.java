package group.service.iko.domain;

import java.util.Date;

public class HistoryRecord {
    private  Date recordDate;
    private int SMR;
    private String recordInformation;
    private String otherInfo;

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
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

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }
}
