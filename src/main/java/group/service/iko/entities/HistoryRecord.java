package group.service.iko.entities;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.util.GregorianCalendar;
import java.util.List;


@Entity
@Table(name = "history_record")
public class HistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column
    private  String title;
    @Column
    private GregorianCalendar recordDate;
    @Column
    private int SMR;
    @Column
    private String recordInformation;
    @ManyToOne()
    @JoinColumn(name="machine_id")
    private Machine machine;
    @Column
    private String otherInfo;
    @OneToMany(mappedBy = "historyRecord", fetch = FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<RecordFile> recordFiles;
    @Column
    private double laborHour;
    @OneToMany(mappedBy = "historyRecord", fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<DetailedLaborHour> laborHours;

    public HistoryRecord() {
    }

    public double getLaborHour() {
        return laborHour;
    }

    public void setLaborHour(double laborHour) {
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

    public GregorianCalendar getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(GregorianCalendar recordDate) {
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
                 ", laborHour=" + laborHour +
                                '}';
    }
}

