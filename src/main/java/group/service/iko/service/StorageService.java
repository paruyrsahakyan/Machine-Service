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
    private final String wareHouseFileFolder=  File.separator + "home" +File.separator + "paruyr" +
            File.separator + "IkoService"+File.separator + "wareHouse";
    private final String wareHouseFileName="wareHouse.xlsx";
    private final String warHouseFilePath =wareHouseFileFolder+File.separator+wareHouseFileName;

    private final String priceListFileFolder=  File.separator + "home" +File.separator + "paruyr" +
            File.separator + "IkoService"+File.separator + "priceList";
    private final String currentPriceListFileName="uploadedPriceList.xlsx";

    private final String requestFileFolder=  File.separator + "home" +File.separator + "paruyr" +
            File.separator + "IkoService"+File.separator + "request";
    private final String currentRequestFileName="uploadedRequest.xlsx";

    private final String currentPriceListFilePath=priceListFileFolder+File.separator+currentPriceListFileName;

    private final String currentRequestFilePath=requestFileFolder+File.separator+currentRequestFileName;

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
        String folderPath =File.separator + "home" +File.separator + "paruyr" + File.separator + "IkoService" + File.separator +
                "fileStorage" + File.separator + customerName + File.separator +
                modelAndSerialNum + File.separator + recordDate + File.separator + fileId;
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

    public void saveWareHouseFile(MultipartFile file) {
        File wareHousefolder = new File(wareHouseFileFolder);
        wareHousefolder.mkdirs();
        File wareHouseFile = new File(warHouseFilePath);
        wareHouseFile.delete();
          try {
            wareHouseFile.createNewFile();
            file.transferTo(wareHouseFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
              }
    public void savePriceListFile(MultipartFile file) {
        File priceListFolder = new File(priceListFileFolder);
        priceListFolder.mkdirs();
        File priceListFile = new File(currentPriceListFilePath);
        priceListFile.delete();
        try {
            priceListFile.createNewFile();
            file.transferTo(priceListFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
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

    public String getWareHouseFileFolder() {
        return wareHouseFileFolder;
    }

    public String getWareHouseFileName() {
        return wareHouseFileName;
    }

    public String getWarHouseFilePath() {
        return warHouseFilePath;
    }
    public String getCurrentPriceListFilePath() {
        return currentPriceListFilePath;
    }

    public String getCurrentRequestFilePath() {
        return currentRequestFilePath;
    }

    public void saveRequestFile(MultipartFile uploadedFile) {
        File requestFolder = new File(requestFileFolder);
         requestFolder.mkdirs();
        File requestFile = new File(currentRequestFilePath);
        requestFile.delete();
        try {
            requestFile.createNewFile();
            uploadedFile.transferTo(requestFile);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}

