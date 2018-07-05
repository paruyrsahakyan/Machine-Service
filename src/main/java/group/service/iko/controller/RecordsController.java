package group.service.iko.controller;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.dto.HistoryRecordDTO;
import group.service.iko.dto.MachineDTO;
import group.service.iko.entities.DetailedLaborHour;
import group.service.iko.entities.HistoryRecord;
import group.service.iko.entities.Machine;
import group.service.iko.entities.RecordFile;
import group.service.iko.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
@Controller()
@RequestMapping("/customer/machine/historyRecord")
public class RecordsController {
    @Autowired
    private MachineService machineService;
    @Autowired
    private RecordFileService recordFileService;
    @Autowired
    private DetailedLaborHourService detailedLaborHourService;
    @Autowired
    private HistoryRecordService historyRecordService;
    @Autowired
    private StorageService storageService;
    @Autowired
    private WorkerService workerService;

    @RequestMapping("/{recordId}")
    public ModelAndView showHistoryRecord(@PathVariable("recordId") int recordId) {
        ModelAndView modelAndView = new ModelAndView("historyRecord/historyRecord");
        HistoryRecord historyRecord = historyRecordService.getHistoryRecordById(recordId);
        List<RecordFile> recordFileList = recordFileService.getFilesByRecordId(recordId);
        int machineId = historyRecordService.getMachineIdByRecordId(recordId);
        modelAndView.addObject("fileList", recordFileList);
        modelAndView.addObject("historyRecord", historyRecord);
        modelAndView.addObject("machineId", machineId);
        modelAndView.addObject("recordDate", new HistoryRecordDTO(historyRecord).getRecordDate() );
        return modelAndView;

    }

    @RequestMapping("/recordList/{machineId}")
    public ModelAndView recordListOfMachine(@PathVariable("machineId") int machineId,
                                            @RequestParam(value = "startDate", defaultValue = "") String startDate,
                                            @RequestParam(value = "endDate", defaultValue = "") String endDate) {

        Machine machine = machineService.getMachineById(machineId);
        List<HistoryRecord> recordList = historyRecordService.getRecordsFilteredByDate(machineId, startDate, endDate);
        ModelAndView modelAndView = new ModelAndView("historyRecord/recordList");
        modelAndView.addObject("recordList", HistoryRecordDTO.transformIntoDTO(recordList));
        modelAndView.addObject("machine", new MachineDTO(machine));
        modelAndView.addObject("startDate", startDate);
        modelAndView.addObject("endDate", endDate);
        return modelAndView;
    }

    @RequestMapping("/createHistoryRecord/{machineId}")
    public ModelAndView createHistoryRecord(@PathVariable("machineId") int machineId) {
        Machine machine = machineService.getMachineById(machineId);
        String model = machine.getModel();
        String serialNumber = machine.getSerialNumber();
        ModelAndView modelAndView = new ModelAndView("historyRecord/createHistoryRecord");
        modelAndView.addObject("machineId", machineId);
        modelAndView.addObject("model", model);
        modelAndView.addObject("serialNumber", serialNumber);
        modelAndView.addObject("workerList", workerService.getAllWorkers());
        return modelAndView;
    }

    @RequestMapping("/newHistoryRecord/{machineId}")

    public ModelAndView newHistoryRecord(@PathVariable("machineId") int machineId,
                                         @RequestParam("title") String title,
                                         @RequestParam(name = "SMR", defaultValue = "0") int SMR,
                                         @RequestParam("date") String date,
                                         @RequestParam(name = "laborHour", defaultValue = "0") String laborhour,
                                         @RequestParam("recordInformation") String recordInformation,
                                         @RequestParam("otherInfo") String otherInfo,
                                         @RequestParam("workerName1") String workerName1,
                                         @RequestParam(name = "manHour1", defaultValue = "0") String manHour1,
                                         @RequestParam("workerName2") String workerName2,
                                         @RequestParam(name = "manHour2", defaultValue = "0") String manHour2,
                                         @RequestParam("workerName3") String workerName3,
                                         @RequestParam(name = "manHour3", defaultValue = "0") String manHour3,
                                         @RequestParam("workerName4") String workerName4,
                                         @RequestParam(name = "manHour4", defaultValue = "0") String manHour4

    ) {
        ModelAndView modelAndView = new ModelAndView("historyRecord/historyRecord");
        Machine machine = machineService.getMachineById(machineId);
        HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setMachine(machine);
        historyRecord.setTitle(title);
        historyRecord.setSMR(SMR);
        historyRecord.setLaborHour(Double.parseDouble(laborhour));
        historyRecord.setRecordInformation(recordInformation);
        historyRecord.setRecordDate(CalendarAdapter.getGregCalendar(date));
        historyRecord.setOtherInfo(otherInfo);
        historyRecordService.saveHistoryRecord(historyRecord);
        HistoryRecord savedHistoryRecord = historyRecordService.getLastRecord();
        int idOfSavedRecord = savedHistoryRecord.getId();
        List<DetailedLaborHour> detailedLaborList = new ArrayList<DetailedLaborHour>();
        detailedLaborList.add(new DetailedLaborHour(workerName1, manHour1));
        detailedLaborList.add(new DetailedLaborHour(workerName2, manHour2));
        detailedLaborList.add(new DetailedLaborHour(workerName3, manHour3));
        detailedLaborList.add(new DetailedLaborHour(workerName4, manHour4));
        for (DetailedLaborHour detailedLabor : detailedLaborList) {
            if (!detailedLabor.getWorkerName().equals("")) {
                detailedLabor.setHistoryRecord(savedHistoryRecord);
                detailedLaborHourService.saveDetailedLaborHour(detailedLabor);
            }
        }
        HistoryRecord recordWithLaborHour = historyRecordService.getHistoryRecordById(idOfSavedRecord);

        modelAndView.addObject("historyRecord", recordWithLaborHour);
        String recordDate = CalendarAdapter.getStringFormat(savedHistoryRecord.getRecordDate());
        modelAndView.addObject("recordDate", recordDate);

        return modelAndView;

    }


    @RequestMapping("/updateHistoryRecord/{historyRecordId}")
    public ModelAndView updateHistoryRecord(@PathVariable("historyRecordId") int historyRecordId) {

        ModelAndView modelAndView = new ModelAndView("historyRecord/updateHistoryRecord");
        HistoryRecord historyRecord = historyRecordService.getHistoryRecordById(historyRecordId);
        modelAndView.addObject("historyRecord", historyRecord);
        String recordDate = CalendarAdapter.getStringFormat(historyRecord.getRecordDate());
        List<DetailedLaborHour> laborHourList = detailedLaborHourService.getDetailedLaborByRecordId(historyRecordId);
        modelAndView.addObject("recordDate", recordDate);
        modelAndView.addObject("laborHourList", laborHourList);
        modelAndView.addObject("workerList", workerService.getAllWorkers());
        return modelAndView;
    }


    @RequestMapping(value = "/updatedHistoryRecord/{historyRecordId}", method = RequestMethod.POST)
    public ModelAndView showUpdatedHistoryRecord(@PathVariable("historyRecordId") int historyRecordId,
                                                 @RequestParam("title") String title,
                                                 @RequestParam(name = "SMR", defaultValue = "0") int SMR,
                                                 @RequestParam("date") String date,
                                                 @RequestParam(name = "laborHour", defaultValue = "0") double laborHour,
                                                 @RequestParam(value = "recordInfo") String recordInfo,
                                                 @RequestParam(value = "otherInfo") String otherInfo,
                                                 @RequestParam(value = "workerName[]") String[] workerNames,
                                                 @RequestParam(value = "manHour[]") String[] manHours

    ) {
        ModelAndView modelAndView = new ModelAndView("historyRecord/historyRecord");
        HistoryRecord historyRecord = historyRecordService.getHistoryRecordById(historyRecordId);
        historyRecord.setTitle(title);
        historyRecord.setSMR(SMR);
        historyRecord.setRecordDate(CalendarAdapter.getGregCalendar(date));
        historyRecord.setLaborHour(laborHour);
        historyRecord.setOtherInfo(otherInfo);
        historyRecord.setRecordInformation(recordInfo);
        historyRecordService.updateHistoryRecord(historyRecord);

        DetailedLaborHour detailedLaborHour;
        detailedLaborHourService.deleteAllByHistoryRecordId(historyRecordId);
        for (int i = 0; i < workerNames.length; i++) {
            if (!workerNames[i].equals("")) {
                detailedLaborHour = new DetailedLaborHour();
                detailedLaborHour.setWorkerName(workerNames[i]);
                detailedLaborHour.setHistoryRecord(historyRecord);
                if (!manHours[i].equals("")) {
                    detailedLaborHour.setJobDuration(Double.parseDouble(manHours[i]));
                }
                detailedLaborHourService.saveDetailedLaborHour(detailedLaborHour);
            }
        }
        HistoryRecord updatedHistoryRecord = historyRecordService.getHistoryRecordById(historyRecordId);
        modelAndView.addObject("historyRecord", updatedHistoryRecord);
        String recordDate = CalendarAdapter.getStringFormat(updatedHistoryRecord.getRecordDate());
        List<DetailedLaborHour> laborHourList = detailedLaborHourService.getDetailedLaborByRecordId(historyRecordId);
        System.out.println(recordDate);
        modelAndView.addObject("recordDate", recordDate);
        modelAndView.addObject("laborHourList", laborHourList);
        return modelAndView;
    }

    @RequestMapping("/deleteHistoryRecord/{historyRecordId}")
    public ModelAndView deleteHistoryRecord(@PathVariable("historyRecordId") int recordId) {
        ModelAndView modelAndView = new ModelAndView("machine/machine");
       int machineId = historyRecordService.getMachineIdByRecordId(recordId);
         HistoryRecord historyRecord = new HistoryRecord();
        historyRecord.setId(recordId);
        historyRecordService.deleteHistoryRecord(historyRecord);
        Machine machine = machineService.getMachineById(machineId);
        modelAndView.addObject("machine", new MachineDTO(machine));

        return modelAndView;
    }

    @RequestMapping("/addFiles/{historyRecordId}")
    public ModelAndView addFiles(@PathVariable("historyRecordId") int historyRecordId) {
        ModelAndView modelAndView = new ModelAndView("recordFiles/filesPage");
        HistoryRecord historyRecord = historyRecordService.getHistoryRecordById(historyRecordId);
        List<RecordFile> fileList = recordFileService.getFilesByRecordId(historyRecordId);
        HistoryRecordDTO historyRecordDTO = new HistoryRecordDTO(historyRecord);
        modelAndView.addObject("historyRecord", historyRecordDTO);
        modelAndView.addObject("fileList", fileList);
        return modelAndView;

    }


    @RequestMapping(value = "/files/updatedList/{historyRecordId}",
            method = RequestMethod.POST)
    public ModelAndView filesUpdated(@PathVariable("historyRecordId") int historyRecordId,
                                     @RequestParam(value = "file1", required = false) MultipartFile file1,
                                     @RequestParam(value = "fileDescription1", defaultValue = "") String fileDescription1,
                                     @RequestParam(value = "file2", required = false) MultipartFile file2,
                                     @RequestParam(value = "fileDescription2", defaultValue = "") String fileDescription2,
                                     @RequestParam(value = "file3", required = false) MultipartFile file3,
                                     @RequestParam(value = "fileDescription3", defaultValue = "") String fileDescription3,
                                     @RequestParam(value = "file4", required = false) MultipartFile file4,
                                     @RequestParam(value = "fileDescription4", defaultValue = "") String fileDescription4,
                                     @RequestParam(value = "checkBox", required = false) int[] filesIdToDelete
    ) {
        ModelAndView modelAndView = new ModelAndView("recordFiles/filesPage");
           if (filesIdToDelete != null) {

            for (int id : filesIdToDelete) {
                recordFileService.deleteFileById(id);
            }
        }

        HistoryRecord historyRecord = historyRecordService.getHistoryRecordById(historyRecordId);
        List<MultipartFile> files = new ArrayList<MultipartFile>();
        files.add(file1);
        files.add(file2);
        files.add(file3);
        files.add(file4);
        List<String> descriptions = new ArrayList<String>();
        descriptions.add(fileDescription1);
        descriptions.add(fileDescription2);
        descriptions.add(fileDescription3);
        descriptions.add(fileDescription4);
        int i = -1;
            for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                storageService.storeFile(file, historyRecordId);
                RecordFile recordFile = new RecordFile();
                recordFile.setFilePath(storageService.getFilePath());
                recordFile.setFileDescription(descriptions.get(files.indexOf(file)));
                recordFile.setFilename(storageService.getFileName());
                recordFile.setHistoryRecord(historyRecord);
                recordFileService.saveFile(recordFile);
            }
        }
        HistoryRecord updatedHistoryRecord = historyRecordService.getHistoryRecordById(historyRecordId);
        List<RecordFile> fileList = recordFileService.getFilesByRecordId(historyRecordId);

        modelAndView.addObject("historyRecord", new HistoryRecordDTO(updatedHistoryRecord));
        modelAndView.addObject("fileList", fileList);
        return modelAndView;
    }


    @RequestMapping(value = "/downloadFile/{fileId}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response,
                             @PathVariable("fileId") int fileId) throws IOException {
        RecordFile recordFile = recordFileService.getRecordFileById(fileId);
        String fileName = recordFile.getFileName();
        File file = new File(recordFile.getFilePath());

        if (!file.exists()) {
            String errorMessage = "Файл который вы загружаете не сушествует!!";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
        String mimeType = URLConnection.guessContentTypeFromName(recordFile.getFileName());
        if (mimeType == null) {
            mimeType = storageService.getFileMimeType(recordFile);
        }
        if (mimeType == null) {
               mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" +
                "Downloaded_From_Iko" + fileName.substring(fileName.lastIndexOf(".")) +  "\""));
        response.setContentLength((int) file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }


}