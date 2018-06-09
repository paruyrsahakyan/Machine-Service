package group.service.iko.dto;

public class JobDTO {
    private int id;
    private int historyRecordId;
    private int machineId;
    private int customerId;
    private String workerName;
    private double jobDuration;
    private String historyRecordTitle;
    private String machine;
    private String customer;
    private String date;

    @Override
    public String toString() {
        return "JobDTO{" +
                "id=" + id +
                ", historyRecordId=" + historyRecordId +
                ", machineId=" + machineId +
                ", customerId=" + customerId +
                ", workerName='" + workerName + '\'' +
                ", jobDuration=" + jobDuration +
                ", historyRecordTitle='" + historyRecordTitle + '\'' +
                ", machine='" + machine + '\'' +
                ", customer='" + customer + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHistoryRecordId() {
        return historyRecordId;
    }

    public void setHistoryRecordId(int historyRecordId) {
        this.historyRecordId = historyRecordId;
    }

    public int getMachineId() {
        return machineId;
    }

    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
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

    public String getHistoryRecordTitle() {
        return historyRecordTitle;
    }

    public void setHistoryRecordTitle(String historyRecordTitle) {
        this.historyRecordTitle = historyRecordTitle;
    }

    public String getMachine() {
        return machine;
    }

    public void setMachine(String machine) {
        this.machine = machine;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
