package TM;

public class PendingOrderTmList {
    private String Code;
    private String SenderName;
    private String Address;
    private double Price;
    private String Date;

    public PendingOrderTmList() {
    }

    public PendingOrderTmList(String code, String senderName, String address, double price, String date) {
        Code = code;
        SenderName = senderName;
        Address = address;
        Price = price;
        Date = date;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getSenderName() {
        return SenderName;
    }

    public void setSenderName(String senderName) {
        SenderName = senderName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "PendingOrderTmList{" +
                "Code='" + Code + '\'' +
                ", SenderName='" + SenderName + '\'' +
                ", Address='" + Address + '\'' +
                ", Price=" + Price +
                ", Date='" + Date + '\'' +
                '}';
    }
}
