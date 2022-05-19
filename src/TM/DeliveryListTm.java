package TM;

import javafx.scene.control.Button;

public class DeliveryListTm {
    private String code;
    private String riderName;
    private String address;
    private double price;
    private double profit;
    private String date;

    public DeliveryListTm() {
    }

    public DeliveryListTm(String code, String riderName, String address, double price, double profit, String date) {
        this.code = code;
        this.riderName = riderName;
        this.address = address;
        this.price = price;
        this.profit = profit;
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
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

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DeliveryListTm{" +
                "code='" + code + '\'' +
                ", riderName='" + riderName + '\'' +
                ", address='" + address + '\'' +
                ", price=" + price +
                ", profit=" + profit +
                ", date='" + date + '\'' +
                '}';
    }
}