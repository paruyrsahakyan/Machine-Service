package group.service.iko.entities;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

@Entity(name = "record_file")
public class RecordFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String filePath;
    @Column
    private String filename;
    @Column
    private String fileDescription;
    @ManyToOne
    @JoinColumn(name="history_record_id")
    private HistoryRecord historyRecord;

    public String getFileDescription() {
        return fileDescription;
    }

    public void setFileDescription(String fileDescription) {
        this.fileDescription = fileDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public HistoryRecord getHistoryRecord() {
        return historyRecord;
    }

    public void setHistoryRecord(HistoryRecord historyRecord) {
        this.historyRecord = historyRecord;
    }

    public String getFileName() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "RecordFile{" +
                "id=" + id +
                ", filePath='" + filePath + '\'' +
                ", filename='" + filename + '\'' +
                ", historyRecord=" + historyRecord.getTitle() +
                '}';
    }
}
