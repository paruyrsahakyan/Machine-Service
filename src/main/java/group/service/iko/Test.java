package group.service.iko;

import group.service.iko.entities.Machine;
import group.service.iko.entities.WorkOrder;
import group.service.iko.service.ExcelReaderWriter;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        ExcelReaderWriter excelReaderWriter = new ExcelReaderWriter();
        WorkOrder workOrder = new WorkOrder();
        Machine machine=new Machine();
        machine.setModel("PC300");
        machine.setSerialNumber("serialNumber");
        workOrder.setMachine(machine);
        excelReaderWriter.createReport(workOrder);
//        excelReader.readInStockParts(filePath);
    }
}
