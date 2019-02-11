package group.service.iko.service;

import group.service.iko.entities.Part;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader {
    private List partsInStock;
    private int partsQuantity;

    public ExcelReader() {
        this.partsInStock = new ArrayList<Part>();
    }

    public List<Part> getPartsInStock() {
        return partsInStock;
    }

    public void setPartsInStock(List<Part> partsInStock) {
        this.partsInStock = partsInStock;
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
}