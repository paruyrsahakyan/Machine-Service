package group.service.iko.service;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.MaintenancePart;
import group.service.iko.entities.Part;
import group.service.iko.entities.WorkOrder;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class ExcelReaderWriter {
    @Autowired
    private StorageService storageService;
    private static Map<String, Part> partMap;
    private int partsQuantity;
    private final String reportFileSource = File.separator + "home" + File.separator + "paruyr" + File.separator +
            "Machine-Service" + File.separator + "src" + File.separator + "main" + File.separator +
            "resources" + File.separator + "templates" + File.separator + "serviceReport.xlsx";
    private final String wareHouseRequestSourceFile = File.separator + "home" + File.separator + "paruyr" + File.separator +
            "Machine-Service" + File.separator + "src" + File.separator + "main" + File.separator +
            "resources" + File.separator + "templates" + File.separator + "wareHouseRequest.xlsx";
    private final String templatesFolder = File.separator + "home" + File.separator + "paruyr" +
            File.separator + "IkoService" + File.separator + "templates";


    public void setPartsFromWareHouseFile() {
        partMap = new HashMap<>();
        try {
            File wareHouseFile = new File(storageService.getWarHouseFilePath());
            FileInputStream excelFile = new FileInputStream(wareHouseFile);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            partsQuantity = datatypeSheet.getLastRowNum() - 8;
                for (int i = 8; i < partsQuantity + 8; i++) {
                Part part = new Part();
                Row row = datatypeSheet.getRow(i);
                String partNumber =row.getCell(0).toString();
                part.setPartNumber(partNumber);
                part.setNomenclature(row.getCell(3).toString());
                part.setUnit(row.getCell(6).getStringCellValue());
                part.setQuantity(row.getCell(7).getNumericCellValue());
                partMap.put(partNumber, part);
            }
            GregorianCalendar now = new GregorianCalendar();
               WareHouseService.updateDate= CalendarAdapter.getStringFormat(now);
               WareHouseService.availablePartList=partMap;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public File getReport(WorkOrder workOrder) throws IOException {
        File sourceFile = new File(reportFileSource);
        File fileFolder = new File(templatesFolder);
        File file = new File(templatesFolder + File.separator + "serviceReport.xlsx");
        fileFolder.mkdir();
        if (file.exists()) file.delete();
        FileUtils.copyFile(sourceFile, file);
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        String machineModel = workOrder.getMachine().getModel();
        String machineSerialNumber = workOrder.getMachine().getSerialNumber();
        String engineModel = workOrder.getMachine().getEngineModel();
        String engineSerialNumber = workOrder.getMachine().getEngineSerialNumber();
        String customer = workOrder.getMachine().getCustomer().getName();
        String location = workOrder.getLocation();
        String reportType = "Коммерция";
        String workType = "ТО";
        String maintenance = "TO" + workOrder.getPeriodicMaintenance().getSmr();
        Cell cellMachineModel = datatypeSheet.getRow(4).getCell(8);
        Cell cellMachineSerialNumber = datatypeSheet.getRow(4).getCell(10);
        Cell cellEngineModel = datatypeSheet.getRow(5).getCell(7);
        Cell cellEngineSerialNumber = datatypeSheet.getRow(5).getCell(10);
        Cell cellCustomer = datatypeSheet.getRow(6).getCell(2);
        Cell cellLocation = datatypeSheet.getRow(6).getCell(10);
        Cell cellReportType = datatypeSheet.getRow(9).getCell(2);
        Cell cellWorkType = datatypeSheet.getRow(9).getCell(7);
        Cell cellMaintenace = datatypeSheet.getRow(14).getCell(1);
        Cell cellWorkNumber = datatypeSheet.getRow(14).getCell(0);
        cellMachineModel.setCellValue(machineModel);
        cellMachineSerialNumber.setCellValue(machineSerialNumber);
        cellEngineModel.setCellValue(engineModel);
        cellEngineSerialNumber.setCellValue(engineSerialNumber);
        cellCustomer.setCellValue(customer);
        cellLocation.setCellValue(location);
        cellReportType.setCellValue(reportType);
        cellWorkType.setCellValue(workType);
        cellWorkNumber.setCellValue("1");
        cellMaintenace.setCellValue(maintenance);
        int partRow = 19;
        int i = 0;
        for (MaintenancePart maintenancePart : workOrder.getPeriodicMaintenance().getMaintenanceParts()) {
            i++;
            Cell position = datatypeSheet.getRow(partRow).getCell(0);
            Cell partNumber = datatypeSheet.getRow(partRow).getCell(1);
            Cell partUnit = datatypeSheet.getRow(partRow).getCell(9);
            Cell partQuantity = datatypeSheet.getRow(partRow).getCell(10);
            position.setCellValue(i);
            partNumber.setCellValue(maintenancePart.getPartNumber());
            partUnit.setCellValue(maintenancePart.getUnit());
            partQuantity.setCellValue(maintenancePart.getQuantity());
            partRow++;
        }

        fileInputStream.close();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileInputStream.close();
        return file;
    }

    public File getWareHouseRequest(WorkOrder workOrder) throws IOException {
        File sourceFile = new File(wareHouseRequestSourceFile);
        File fileFolder = new File(templatesFolder);
        File wareHouseRequestFile = new File(templatesFolder + File.separator + "warehouseRequest.xlsx");
        fileFolder.mkdir();
        if (wareHouseRequestFile.exists()) wareHouseRequestFile.delete();
        FileUtils.copyFile(sourceFile, wareHouseRequestFile);
        FileInputStream fileInputStream = new FileInputStream(wareHouseRequestFile);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        String machineModel = workOrder.getMachine().getModel();
        String machineSerialNumber = workOrder.getMachine().getSerialNumber();
        String customer = workOrder.getMachine().getCustomer().getName();
        String location = workOrder.getLocation();
        String worker = workOrder.getWorker();
        String date = CalendarAdapter.getStringFormat(workOrder.getOrderDate());
        String maintenance = "TO" + workOrder.getPeriodicMaintenance().getSmr();
        Cell cellWorker = datatypeSheet.getRow(23).getCell(2);
        Cell cellCustomer =datatypeSheet.getRow(24).getCell(2);
        Cell cellWorkToDo = datatypeSheet.getRow(25).getCell(2);
        Cell cellLocation = datatypeSheet.getRow(26).getCell(2);
        Cell cellDate = datatypeSheet.getRow(1).getCell(4);
        cellWorker.setCellValue(worker);
        cellCustomer.setCellValue(customer);
        cellWorkToDo.setCellValue(machineModel+" sn" +machineSerialNumber +"; " + maintenance);
        cellLocation.setCellValue(location);
        cellDate.setCellValue(date);
        int partRow = 6;
        int i = 0;
        for (MaintenancePart maintenancePart : workOrder.getPeriodicMaintenance().getMaintenanceParts()) {
            Cell cellPartNumber = datatypeSheet.getRow(partRow).getCell(1);
            Cell cellPartDescription = datatypeSheet.getRow(partRow).getCell(2);
            Cell cellPartUnit = datatypeSheet.getRow(partRow).getCell(3);
            Cell cellPartQuantity = datatypeSheet.getRow(partRow).getCell(4);
            Cell cellAvailable  = datatypeSheet.getRow(partRow).getCell(5);

            String partNumber = maintenancePart.getPartNumber();
            Double available =partMap.get(partNumber).getQuantity();

            cellPartNumber.setCellValue(partNumber);
            cellPartDescription.setCellValue(maintenancePart.getPartType());
            cellPartUnit.setCellValue(maintenancePart.getUnit());
            cellPartQuantity.setCellValue(maintenancePart.getQuantity());
            cellAvailable.setCellValue(available);
            partRow++;
        }

        fileInputStream.close();
        FileOutputStream fileOutputStream = new FileOutputStream(wareHouseRequestFile);
        workbook.write(fileOutputStream);
        fileInputStream.close();
        return wareHouseRequestFile;

    }

    public Map<String, Part> getPartMap() {
        return partMap;
    }

    public void setPartMap(Map<String, Part> partMap) {
        this.partMap = partMap;
    }
}