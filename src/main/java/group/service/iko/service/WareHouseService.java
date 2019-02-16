package group.service.iko.service;

import group.service.iko.entities.InterChangeableGroup;
import group.service.iko.entities.InterChangeablePart;
import group.service.iko.entities.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class WareHouseService {
    public static String updateDate;
    private List<Part> availablePartList;
    @Autowired
    private InterChangeablePartService interChangeablePartService;

    @Override
    public String toString() {
        return "WareHouseService{" +
                "availablePartList=" + availablePartList +
                '}';
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
            for(InterChangeableGroup interChangeableGroup2: interChangeableGroupList) {
                if (interChangeableGroup2.getBasicPartNumber().equals(basicPartNumber)) {
                    exist = true;
                }
            }
            if(!exist) {
                interChangeableGroupList.add(interChangeableGroup)};
            }
        
        return interChangeableGroupList;
    }
}