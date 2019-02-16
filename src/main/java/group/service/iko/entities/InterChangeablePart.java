package group.service.iko.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name="interchangeable_part")
public class InterChangeablePart {
    @Id
    private int id;
    @Column (name = "basic_part_number");
    private String basicPartNumber;
    @Column(name = "part_number")
    public String partNumber;

   public String getBasicPartNumber() {
        return basicPartNumber;
    }

    public void setBasicPartNumber(String basicPartNumber) {
        this.basicPartNumber = basicPartNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }
}
