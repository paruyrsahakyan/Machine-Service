package group.service.iko;

import group.service.iko.service.ExcelReader;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.File;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
        ExcelReader excelReader = new ExcelReader();
        String folderPath = "C:\\Users\\Paruyr\\Desktop" ;
    String filePath= folderPath+File.separator+"parts.xlsx";
//    System.out.println(folderPath);
//        File folder = new File(folderPath);
//        folder.mkdirs();
//        File newFile = new File(filePath);
//        try {
//            newFile.createNewFile();
//                    } catch (IOException e) {
//            e.printStackTrace();
//        }

        excelReader.readInStockParts(filePath);
    }
}
