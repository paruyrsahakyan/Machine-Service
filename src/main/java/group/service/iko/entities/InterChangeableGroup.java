package group.service.iko.entities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InterChangeableGroup {

  private  String basicPartNumber;
  private  Set<String> interChangeablePartsList = new HashSet<>();

    public String getBasicPartNumber() {
        return basicPartNumber;
    }

    public void setBasicPartNumber(String basicPartNumber) {
        this.basicPartNumber = basicPartNumber;
    }

    public Set<String> getInterChangeablePartsList() {
        return interChangeablePartsList;
    }

    public void setInterChangeablePartsList(Set<String> interChangeablePartsList) {
        this.interChangeablePartsList = interChangeablePartsList;
    }
}
