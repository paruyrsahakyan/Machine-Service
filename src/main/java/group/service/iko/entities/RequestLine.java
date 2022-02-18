package group.service.iko.entities;

public class RequestLine {

    private  int lineNumber;
    private  String partName;
    private  String partNumber;
    int quantity;


    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    @Override
    public String toString() {
        return "RequestLine{" +
                "partName='" + partName + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
