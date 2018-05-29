package group.service.iko.service;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.Customer;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class StorageService {

    public void storeFile(MultipartFile file, int historyRecordId){

        HistoryRecordService historyRecordService = new HistoryRecordService();
        HistoryRecord historyRecord = historyRecordService.getHistoryRecordById(historyRecordId);
        String recordDate = CalendarAdapter.getStringFormat(historyRecord.getRecordDate());
        Machine machine = historyRecord.getMachine();
        String modelAndSerialNum = machine.getModel()+"; sn "+machine.getSerialNumber();
        Customer customer = machine.getCustomer();
        String customerName = customer.getName();
        String folderPath = System.getProperty("user.home")+File.separator+"IkoService"+File.separator+
                "fileStorage"+ File.separator+customerName +File.separator+
                modelAndSerialNum+File.separator+recordDate;
        System.out.println(folderPath);
        String filePath = folderPath+File.separator+file.getOriginalFilename();
        File folder = new File(folderPath);
        System.out.println(folder.mkdirs());
        System.out.println(filePath);
        File newFile = new File(filePath);
              try {
            newFile.createNewFile();
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
