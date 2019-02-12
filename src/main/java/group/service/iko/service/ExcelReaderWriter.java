package group.service.iko.service;

import group.service.iko.entities.MaintenancePart;
import group.service.iko.entities.Part;
import group.service.iko.entities.WorkOrder;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelReaderWriter {
    private List partsInStock;
    private int partsQuantity;
    private final String reportFileSource = File.separator + "home" +File.separator + "paruyr" + File.separator +
            "Machine-Service"+File.separator + "src" + File.separator +"main"+File.separator+
            "resources" + File.separator + "templates"+File.separator+"serviceReport.xlsx";
    private final String folderToCopyServiceReport=  File.separator + "home" +File.separator + "paruyr" +
            File.separator + "IkoService"+File.separator + "templates";


    public ExcelReaderWriter() {
        this.partsInStock = new ArrayList<Part>();
    }

    public void readInStockParts(String filePath) {
        try {
            FileInputStream excelFile = new FileInputStream(new File(filePath));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            partsQuantity = datatypeSheet.getLastRowNum() - 8;
            System.out.println(datatypeSheet.getLastRowNum());

            for (int i = 8; i < partsQuantity + 8; i++) {
                Part part = new Part();
                Row row = datatypeSheet.getRow(i);
                part.setPartNumber(row.getCell(0).toString());
                part.setNomenclature(row.getCell(3).toString());
                part.setUnit(row.getCell(6).getStringCellValue());
                part.setQuantity(row.getCell(7).getNumericCellValue());
                partsInStock.add(part);
                System.out.println(partsInStock);
            }

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public File getReport(WorkOrder workOrder) throws IOException {
        File sourceFile = new File(reportFileSource);
        File fileFolder = new File(folderToCopyServiceReport);
        File file = new File( folderToCopyServiceReport+File.separator+"serviceReport.xlsx");
        fileFolder.mkdir();
        if (file.exists()) file.delete();
        FileUtils.copyFile(sourceFile, file);
        System.out.println(file.exists());
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
        String maintenance ="TO"+workOrder.getPeriodicMaintenance().getSmr();
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
        int partRow = 21;
        int i = 0;
        for(MaintenancePart maintenancePart: workOrder.getPeriodicMaintenance().getMaintenanceParts()) {
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

         String model= workOrder.getMachine().getModel();
        System.out.println(model);
        cellMachineModel.setCellValue(workOrder.getMachine().getModel());

        fileInputStream.close();

       FileOutputStream fileOutputStream =new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileInputStream.close();
        return file;
    }

    public List<Part> getPartsInStock() {
        return partsInStock;
    }

    public void setPartsInStock(List<Part> partsInStock) {
        this.partsInStock = partsInStock;
    }

    public String getFolderToCopyServiceReport() {
        return folderToCopyServiceReport;
    }
}