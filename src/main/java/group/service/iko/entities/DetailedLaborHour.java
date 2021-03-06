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
    private double jobDuration;
    @ManyToOne()
    @JoinColumn(name = "history_record_id")
    private HistoryRecord historyRecord;

    public DetailedLaborHour(){

    }

    public DetailedLaborHour(String workerName, String jobDuration) {

        this.workerName = workerName;
        this.jobDuration = Double.parseDouble(jobDuration);
    }


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

    public double getJobDuration() {
        return jobDuration;
    }

    public void setJobDuration(double jobDuration) {
        this.jobDuration = jobDuration;
    }

    public HistoryRecord getHistoryRecord() {
        return historyRecord;
    }

    public void setHistoryRecord(HistoryRecord historyRecord) {
        this.historyRecord = historyRecord;
    }

    @Override
    public String toString() {
        return "DetailedLaborHour{" +
                "id=" + id +
                ", workerName='" + workerName + '\'' +
                ", jobDuration=" + jobDuration +
                ", historyRecord=" + historyRecord +
                '}';
    }
}
