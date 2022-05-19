package TM;

public class PlaceOrderTableTm {
    private String Code;
    private String RiderName;
    private String Address;
    private double Price;
    private double CompanyProfit;
    private String Date;


    public PlaceOrderTableTm() {
    }

    public PlaceOrderTableTm(String code, String riderName, String address, double price, double companyProfit, String date) {
        Code = code;
        RiderName = riderName;
        Address = address;
        Price = price;
        CompanyProfit = companyProfit;
        Date = date;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getRiderName() {
        return RiderName;
    }

    public void setRiderName(String riderName) {
        RiderName = riderName;
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

    public double getCompanyProfit() {
        return CompanyProfit;
    }

    public void setCompanyProfit(double companyProfit) {
        CompanyProfit = companyProfit;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "PlaceOrderTableTm{" +
                "Code='" + Code + '\'' +
                ", RiderName='" + RiderName + '\'' +
                ", Address='" + Address + '\'' +
                ", Price=" + Price +
                ", CompanyProfit=" + CompanyProfit +
                ", Date='" + Date + '\'' +
                '}';
    }
}
