package group.service.iko.entities;
import javax.persistence.*;

@Entity
@Table(name = "detailed_labor_hour")
public class DetailedLaborHour {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String workerName;
    @Column
    private int jobDuration;
    @ManyToOne()
    @JoinColumn(name = "history_record_id")
    private HistoryRecord historyRecord;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public int getJobDuration() {
        return jobDuration;
    }

    public void setJobDuration(int jobDuration) {
        this.jobDuration = jobDuration;
    }

    public HistoryRecord getHistoryRecord() {
        return historyRecord;
    }

    public void setHistoryRecord(HistoryRecord historyRecord) {
        this.historyRecord = historyRecord;
    }
}
