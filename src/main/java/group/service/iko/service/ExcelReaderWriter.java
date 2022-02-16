package group.service.iko.service;

import group.service.iko.calendarAdapter.CalendarAdapter;
import group.service.iko.entities.*;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.io.FileInputStream;

import java.util.*;

@Service
public class ExcelReaderWriter {
    @Autowired
    private StorageService storageService;
    @Autowired
    private InterChangeablePartService interChangeablePartServicel;
    public static Map<String, Part> partMap;
    private int partsQuantity;
    private final String reportFileSource = File.separator + "home" + File.separator + "paruyr" + File.separator +
            "Machine-Service" + File.separator + "src" + File.separator + "main" + File.separator +
            "resources" + File.separator + "templates" + File.separator + "serviceReport.xlsx";
    private final String wareHouseRequestSourceFile = File.separator + "home" + File.separator + "paruyr" + File.separator +
            "Machine-Service" + File.separator + "src" + File.separator + "main" + File.separator +
            "resources" + File.separator + "templates" + File.separator + "wareHouseRequest.xlsx";
    private final String templatesFolder = File.separator + "home" + File.separator + "paruyr" +
            File.separator + "IkoService" + File.separator + "templates";
    private final String maintenanceRequestFileSource = File.separator + "home" + File.separator + "paruyr" + File.separator +
            "Machine-Service" + File.separator + "src" + File.separator + "main" + File.separator +
            "resources" + File.separator + "templates" + File.separator + "maintenanceRequest.xlsx";


    public void setPartsFromWareHouseFile() throws Throwable {
        partMap = new HashMap<>();
        try {
            File wareHouseFile = new File(new StorageService().getWarHouseFilePath());
            FileInputStream excelFile = new FileInputStream(wareHouseFile);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int firstPartRow = 0;
            int partNumberColumn = 0;
            int nomenclatureColumn = 0;
            int unitColumn = 0;
            int quantityColumn = 0;
            int netCostColumn = 0;
            for (int i = 0; i < 30; i++) {
                Row row = datatypeSheet.getRow(i);
                for (int j = 0; j <= row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell == null) continue;
                    if (cell.getCellTypeEnum() != CellType.STRING)
                        continue;
                    String cellText = cell.getStringCellValue();
                    if (cellText.equals("Артикул")) {
                        partNumberColumn = j;
                        firstPartRow = i + 2;
                    }
                    if (cellText.equals("Номенклатура")) {
                        nomenclatureColumn = j;
                    }
                    if (cellText.equals("Количество")) {
                        quantityColumn = j;

                    }
                    if (cellText.equals("Себестоимость ЕД.")) {
                        netCostColumn = j;
                    }
                }
                if (netCostColumn != 0) break;
            }


            partsQuantity = datatypeSheet.getLastRowNum() - 8;
            for (int i = firstPartRow; i < partsQuantity + 8; i++) {
                Part part = new Part();
                Row row = datatypeSheet.getRow(i);
                Cell partNumberCell = row.getCell(partNumberColumn);
                String partNumber = "";
                if (partNumberCell.getCellTypeEnum() == CellType.STRING) {
                    partNumber = partNumberCell.getStringCellValue();

                } else {
                    String string = new Double(partNumberCell.getNumericCellValue()).toString();
                    int lastIndexOf = string.lastIndexOf(".");
                    partNumber = string.substring(0, lastIndexOf);
                }
                part.setPartNumber(partNumber);
                part.setNomenclature(row.getCell(nomenclatureColumn).toString());
//                part.setUnit(row.getCell(6).getStringCellValue());
                Cell quantityCell = row.getCell(quantityColumn);
                if (quantityCell.getCellTypeEnum() == CellType.NUMERIC) {
                    part.setQuantity(quantityCell.getNumericCellValue());
                }
                Cell netCostCell = row.getCell(netCostColumn);
                if (netCostCell.getCellTypeEnum() == CellType.NUMERIC) {
                    part.setNetCost(netCostCell.getNumericCellValue());
                }
                partMap.put(partNumber, part);
            }
            GregorianCalendar now = new GregorianCalendar();
            WareHouseService.updateDate = CalendarAdapter.getStringFormat(now);
            WareHouseService.availablePartList = partMap;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public File getMaintenanceRequest(WorkOrder workOrder) throws IOException {
        File sourceFile = new File(maintenanceRequestFileSource);
        File fileFolder = new File(templatesFolder);
        fileFolder.mkdir();
        File file = new File(templatesFolder + File.separator + "maintenanceRequest.xlsx");
        if (file.exists()) file.delete();
        FileUtils.copyFile(sourceFile, file);
        FileInputStream fileInputStream = new FileInputStream(file);
        Workbook workbook = new XSSFWorkbook(fileInputStream);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        String machineModel = workOrder.getMachine().getModel();
        String machineSerialNumber = workOrder.getMachine().getSerialNumber();
        String customer = workOrder.getMachine().getCustomer().getName();
        String location = workOrder.getLocation();
        String palanedDate = CalendarAdapter.getStringFormat(workOrder.getOrderDate());

        int smr = workOrder.getOrderSmr();
        String maintenance = "TO" + workOrder.getPeriodicMaintenance().getSmr();

        Cell cellCustomer = datatypeSheet.getRow(4).getCell(3);
        Cell cellMachineModel = datatypeSheet.getRow(6).getCell(4);
        Cell cellMachineSerialNumber = datatypeSheet.getRow(7).getCell(5);
        Cell cellSMR = datatypeSheet.getRow(8).getCell(6);
        Cell cellPlanedDate = datatypeSheet.getRow(9).getCell(5);
        Cell cellMaintenace = datatypeSheet.getRow(10).getCell(9);
        Cell cellLocation = datatypeSheet.getRow(22).getCell(5);

        cellCustomer.setCellValue(customer);
        cellMachineModel.setCellValue(machineModel);
        cellMachineSerialNumber.setCellValue(machineSerialNumber);
        cellSMR.setCellValue(smr);
        cellPlanedDate.setCellValue(palanedDate);
        cellLocation.setCellValue(location);
        cellMaintenace.setCellValue(maintenance);
        fileInputStream.close();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);
        fileInputStream.close();
        return file;
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
            Cell cellPosition = datatypeSheet.getRow(partRow).getCell(0);
            Cell cellPartNumber = datatypeSheet.getRow(partRow).getCell(1);
            Cell cellPartUnit = datatypeSheet.getRow(partRow).getCell(9);
            Cell cellPartQuantity = datatypeSheet.getRow(partRow).getCell(10);

            String partNumber = maintenancePart.getPartNumber();
            if (partMap.containsKey(partNumber)) {
            } else {
                Part availableInterChangeablePart = WareHouseService.getAvailableInterchangeablePart(partNumber);
                if (availableInterChangeablePart != null) {
                    partNumber = availableInterChangeablePart.getPartNumber();
                }
            }

            cellPosition.setCellValue(i);
            cellPartNumber.setCellValue(partNumber);
            cellPartUnit.setCellValue(maintenancePart.getUnit());
            cellPartQuantity.setCellValue(maintenancePart.getQuantity());
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
        String serviceMachine = workOrder.getServiceMachine();
        String location = workOrder.getLocation();
        String worker = workOrder.getWorker();
        String date = CalendarAdapter.getStringFormat(workOrder.getOrderDate());
        String maintenance = "TO" + workOrder.getPeriodicMaintenance().getSmr();
        Cell cellWorker = datatypeSheet.getRow(23).getCell(2);
        Cell cellServiceMachine = datatypeSheet.getRow(24).getCell(2);
        Cell cellCustomer = datatypeSheet.getRow(25).getCell(2);
        Cell cellWorkToDo = datatypeSheet.getRow(26).getCell(2);
        Cell cellLocation = datatypeSheet.getRow(27).getCell(2);
        Cell cellDate = datatypeSheet.getRow(1).getCell(4);
        cellWorker.setCellValue(worker);
        cellServiceMachine.setCellValue(serviceMachine);
        cellCustomer.setCellValue(customer);
        cellWorkToDo.setCellValue(machineModel + " sn" + machineSerialNumber + "; " + maintenance);
        cellLocation.setCellValue(location);
        cellDate.setCellValue(date);
        int partRow = 6;
        int i = 0;
        for (MaintenancePart maintenancePart : workOrder.getPeriodicMaintenance().getMaintenanceParts()) {
            Cell cellPartNumber = datatypeSheet.getRow(partRow).getCell(1);
            Cell cellPartDescription = datatypeSheet.getRow(partRow).getCell(2);
            Cell cellPartUnit = datatypeSheet.getRow(partRow).getCell(3);
            Cell cellPartQuantity = datatypeSheet.getRow(partRow).getCell(4);
            Cell cellAvailable = datatypeSheet.getRow(partRow).getCell(5);

            String partNumber = maintenancePart.getPartNumber();
            double available = 0;
            if (partMap.containsKey(partNumber)) {
                available = partMap.get(partNumber).getQuantity();
            } else {
                Part availableInterChangeablePart = WareHouseService.getAvailableInterchangeablePart(partNumber);
                if (availableInterChangeablePart != null) {
                    partNumber = availableInterChangeablePart.getPartNumber();
                    available = availableInterChangeablePart.getQuantity();
                }
            }
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

    public static Map<String, Part> getPartMap() {
        return partMap;
    }

    public static void setPartMap(Map<String, Part> partMap) {
        ExcelReaderWriter.partMap = partMap;
    }

    public int getPartsQuantity() {
        return partsQuantity;
    }

    public void setPartsQuantity(int partsQuantity) {
        this.partsQuantity = partsQuantity;
    }

    public Set<PriceForCustomer> getPriceListFromTheFile() throws IOException {
        File priceListFile = new File(storageService.getCurrentPriceListFilePath());
        FileInputStream excelFile = new FileInputStream(priceListFile);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        Set<PriceForCustomer> priceListForCustomer = new HashSet<>();
        int linesQuantityInTheFile = datatypeSheet.getLastRowNum() + 1;
        for (int i = 0; i < linesQuantityInTheFile; i++) {
            PriceForCustomer priceForCustomer = new PriceForCustomer();
            Row row = datatypeSheet.getRow(i);
            Cell articleCell = row.getCell(0);
            String partArticle = articleCell.getStringCellValue();
            priceForCustomer.setArticle(partArticle);
            Cell priceCell = row.getCell(1);
            if (priceCell.getCellTypeEnum() == CellType.NUMERIC) {
                priceForCustomer.setPrice((int) priceCell.getNumericCellValue());
            }
            priceListForCustomer.add(priceForCustomer);
        }
        return priceListForCustomer;
    }

    public List<RequestLine> getRequestFromStoredFile(Customer customer) throws IOException {

        File requestFile = new File(storageService.getCurrentRequestFilePath());
        FileInputStream excelFile = new FileInputStream(requestFile);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);
        List<RequestLine> requestLines = new ArrayList<>();
        int linesQuantityInTheFile = datatypeSheet.getLastRowNum() + 1;
        for (int i = 0; i < linesQuantityInTheFile; i++) {
            Row row = datatypeSheet.getRow(i);
            Cell descriptionCell = row.getCell(0);
            Cell articleCell = row.getCell(1);
            Cell quantityCell = row.getCell(2);
            String partName = descriptionCell.getStringCellValue();
            String partNumber = "";
            if (articleCell.getCellTypeEnum() == CellType.STRING) {
                partNumber = articleCell.getStringCellValue();

            } else {
                String string = new Double(articleCell.getNumericCellValue()).toString();
                int lastIndexOf = string.lastIndexOf(".");
                partNumber = string.substring(0, lastIndexOf);
            }

            int quantity = (int) quantityCell.getNumericCellValue();
            RequestLine requestLine = new RequestLine();
            requestLine.setPartName(partName);
            requestLine.setPartNumber(partNumber);
            requestLine.setQuantity(quantity);
            requestLines.add(requestLine);

                }
        return requestLines;
    }

    public void setSupplierPriceListFile() throws  Throwable {
        Map priceMap = new HashMap<String, Double>();
                    File wareHouseFile = new File(new StorageService().getWarHouseFilePath());
            FileInputStream excelFile = new FileInputStream(wareHouseFile);
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheetAt(0);
            int partNumberColumn = 0;
            int priceColumn = 1;
            partsQuantity = datatypeSheet.getLastRowNum();
            for (int i = 0; i < partsQuantity; i++) {
                   Row row = datatypeSheet.getRow(i);
                Cell partNumberCell = row.getCell(partNumberColumn);
                String partNumber = "";
                if (partNumberCell.getCellTypeEnum() == CellType.STRING) {
                    partNumber = partNumberCell.getStringCellValue();

                } else {
                    String string = new Double(partNumberCell.getNumericCellValue()).toString();
                    int lastIndexOf = string.lastIndexOf(".");
                    partNumber = string.substring(0, lastIndexOf);
                }
                Cell priceCell = row.getCell(priceColumn);
                Double price = new Double(0);
                if (priceCell != null & priceCell.getCellTypeEnum()!=CellType.ERROR) {
                    price = new Double(priceCell.getNumericCellValue());
                }

                priceMap.put(partNumber, price);
            }

            new WareHouseService().setSupplierPriceList(priceMap);
        }
    }




