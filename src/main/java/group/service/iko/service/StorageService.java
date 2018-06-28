package group.service.iko.service;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.Customer;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import group.service.iko.entities.RecordFile;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service
public class StorageService {
    @Autowired
    private HistoryRecordService historyRecordService;
    @Autowired
    private RecordFileService recordFileService;

    private String filePath;
    private String fileName;

    public StorageService() {

    }

    public void storeFile(MultipartFile file, int historyRecordId) {

        HistoryRecord historyRecord = historyRecordService.getHistoryRecordById(historyRecordId);
        String recordDate = CalendarAdapter.getStringFormat(historyRecord.getRecordDate());
        Machine machine = historyRecord.getMachine();
        String modelAndSerialNum = machine.getModel() + "; sn " + machine.getSerialNumber();
        Customer customer = machine.getCustomer();
        String customerName = customer.getName();
        int fileId = recordFileService.getNextId();
        String folderPath = "/home/paruyr/insideStorageService";
        this.filePath = folderPath + File.separator + file.getOriginalFilename();
        this.fileName = file.getOriginalFilename();
        File folder = new File(folderPath);
        folder.mkdirs();
        File newFile = new File(filePath);
        try {
            newFile.createNewFile();
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileMimeType(RecordFile recordFile) {
        String fileName = recordFile.getFileName();
        String mimeType = null;
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        System.out.println(type);
        switch (type) {

            case "doc":
                mimeType = "application/msword";
                break;
            case "xls":
                mimeType = "application/vnd.ms-excel";
                break;
            case "xlsx":
                mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                break;
            case "pdf":
                mimeType = "application/pdf";
                break;
        }
        return mimeType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
