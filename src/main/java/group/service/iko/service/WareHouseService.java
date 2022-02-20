package group.service.iko.service;

import group.service.iko.entities.InterChangeableGroup;
import group.service.iko.entities.InterChangeablePart;
import group.service.iko.entities.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class WareHouseService {
    public static String updateDate;
    public static Map<String, Part> availablePartList;
    @Autowired
    private InterChangeablePartService interChangeablePartService;
    @Autowired
    private ExcelReaderWriter excelReaderWriter;
    @Autowired
    StorageService storageService;

    public  static  Map<String, Double> supplierPriceList;
    public  static  Map<String, String> interchangeabilityMap;


    @Override
    public String toString() {
        return "WareHouseService{" +
                "availablePartList=" + availablePartList +
                '}';
    }

    public WareHouseService() {
    }

    public static String getUpdateDate() {
        return updateDate;
    }

    public static void setUpdateDate(String updateDate) {
        WareHouseService.updateDate = updateDate;
    }

    public Set<InterChangeableGroup> getInterChangeableGroupList() {
        List<InterChangeablePart> interChangeablePartList = interChangeablePartService.getAllInterChangeableParts();
        Set<InterChangeableGroup> interChangeableGroupList = new HashSet<>();
        for (InterChangeablePart interChangeablePart : interChangeablePartList) {
            InterChangeableGroup interChangeableGroup = new InterChangeableGroup();
            String basicPartNumber = interChangeablePart.getBasicPartNumber();
            interChangeableGroup.setBasicPartNumber(basicPartNumber);
            for (InterChangeablePart interChangeablePart1 : interChangeablePartList) {
                if (basicPartNumber.equals(interChangeablePart1.getBasicPartNumber())) {
                    interChangeableGroup.getInterChangeablePartsList().add(interChangeablePart1.getPartNumber());
                }
            }
            boolean exist = false;
            for (InterChangeableGroup interChangeableGroup2 : interChangeableGroupList) {
                if (interChangeableGroup2.getBasicPartNumber().equals(basicPartNumber)) {
                    exist = true;
                }
            }
            if (!exist) {
                interChangeableGroupList.add(interChangeableGroup);
            }

        }

        return interChangeableGroupList;
    }

    public InterChangeableGroup getInterChangeableGroupByBasic(String basicPartNumber) {
        List<InterChangeablePart> interChangeablePartList = interChangeablePartService.getInterChangeableGroupParts(basicPartNumber);
        InterChangeableGroup interChangeableGroup = new InterChangeableGroup();
        interChangeableGroup.setBasicPartNumber(basicPartNumber);
        for (InterChangeablePart interChangeablePart : interChangeablePartList) {
            interChangeableGroup.getInterChangeablePartsList().add(interChangeablePart.getPartNumber());
        }
        return interChangeableGroup;

    }


    public void updateWareHouse(MultipartFile multipartFile) throws Throwable {
        storageService.saveWareHouseFile(multipartFile);
        excelReaderWriter.setPartsFromWareHouseFile();

    }

    public static Part getAvailableInterchangeablePart(String partNumber) {
        InterChangeablePartService interChangeablePartService = new InterChangeablePartService();
        List<InterChangeablePart> interChangeablePartList = interChangeablePartService.getApplicablePartsListByPartNumber(partNumber);
        if (interChangeablePartList == null) {
            return null;
        }
        for (InterChangeablePart interChangeablePart : interChangeablePartList) {
            String partNumber1 = interChangeablePart.getPartNumber();
            if (availablePartList.containsKey(partNumber1)) return availablePartList.get(partNumber1);
        }
        return null;
    }

    public Map<String, Part> getAvailablePartList () throws Throwable {
        if (availablePartList == null) {
            new ExcelReaderWriter().setPartsFromWareHouseFile();
                    }
        return availablePartList;

    }


  public void setSupplierPriceList (MultipartFile multipartFile) throws Throwable {
      storageService.saveSupplierPriceListFile(multipartFile);
      excelReaderWriter.setSupplierPriceListFile();
  }

    public  void setSupplierPriceList(Map<String, Double> supplierPriceList) {
        WareHouseService.supplierPriceList = supplierPriceList;
    }

    public static Map<String, String> getInterchangeabilityMap() {
        return interchangeabilityMap;
    }

    public static void setInterchangeabilityMap(Map<String, String> interchangeabilityMap) {
        WareHouseService.interchangeabilityMap = interchangeabilityMap;
    }
}