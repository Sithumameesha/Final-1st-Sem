package TM;

import javafx.scene.control.Button;

public class PendingOrderTm {
    private String code;
    private String senderName;
    private String address;
    private double price;
    private String date;

    public PendingOrderTm() {
    }

    public PendingOrderTm(String code, String senderName, String address, double price, String date) {
        this.code = code;
        this.senderName = senderName;
        this.address = address;
        this.price = price;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "PendingOrderTm{" +
                "code='" + code + '\'' +
                ", senderName='" + senderName + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                '}';
    }
}
