package model;

public class OrderDetails {
   private String Address;
   private double Price;

    public OrderDetails() {
    }

    public OrderDetails(String address, double price) {
        Address = address;
        Price = price;
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

    @Override
    public String toString() {
        return "OrderDetails{" +
                "Address='" + Address + '\'' +
                ", Price=" + Price +
                '}';
    }
}
