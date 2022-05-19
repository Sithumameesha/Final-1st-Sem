package TM;

public class RiderDetailsTm {
    private String id;
    private String riderName;
    private String address;
    private int contact;
    private double riderPayment;
    private String date;

    public RiderDetailsTm() {
    }

    public RiderDetailsTm(String id, String riderName, String address, int contact, double riderPayment, String date) {
        this.id = id;
        this.riderName = riderName;
        this.address = address;
        this.contact = contact;
        this.riderPayment = riderPayment;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public double getRiderPayment() {
        return riderPayment;
    }

    public void setRiderPayment(double riderPayment) {
        this.riderPayment = riderPayment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RiderDetailsTm{" +
                "id='" + id + '\'' +
                ", riderName='" + riderName + '\'' +
                ", address='" + address + '\'' +
                ", contact=" + contact +
                ", riderPayment=" + riderPayment +
                ", date='" + date + '\'' +
                '}';
    }
}

