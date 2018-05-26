package group.service.iko.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "history_record")
public class HistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;
    @Column
    private  String title;
    @Column
    private  Date recordDate;
    @Column
    private int SMR;
    @Column
    private String recordInformation;
    @ManyToOne()
    @JoinColumn(name="machine_id")
    private Machine machine;
    @Column
    private String otherInfo;
    @OneToMany(mappedBy = "historyRecord", fetch = FetchType.EAGER)
    private List<RecordFile> recordFiles;
    @Column
    private int laborHour;
    @OneToMany(mappedBy = "historyRecord" )
    private List<DetailedLaborHour> laborHours;

    public HistoryRecord() {
    }

    public int getLaborHour() {
        return laborHour;
    }

    public void setLaborHour(int laborHour) {
        this.laborHour = laborHour;
    }

    public List<DetailedLaborHour> getLaborHours() {
        return laborHours;
    }

    public void setLaborHours(List<DetailedLaborHour> laborHours) {
        this.laborHours = laborHours;
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

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    public List<RecordFile> getRecordFiles() {
        return recordFiles;
    }

    public void setRecordFiles(List<RecordFile> recordFiles) {
        this.recordFiles = recordFiles;
    }

    @Override
    public String toString() {
        return "HistoryRecord{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", recordDate=" + recordDate +
                ", SMR=" + SMR +
                ", recordInformation='" + recordInformation + '\'' +
                ", machine=" + machine +
                ", otherInfo='" + otherInfo + '\'' +
                ", recordFiles=" + recordFiles +
                ", laborHour=" + laborHour +
                ", laborHours=" + laborHours +
                '}';
    }
}

