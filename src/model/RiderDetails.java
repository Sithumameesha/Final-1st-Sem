package model;

public class RiderDetails {
    private String Name;
    private String Id;
    private String Address;
    private int Contact;

    public RiderDetails() {
    }

    public RiderDetails(String name, String id, String address, int contact) {
        Name = name;
        Id = id;
        Address = address;
        Contact = contact;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getContact() {
        return Contact;
    }

    public void setContact(int contact) {
        Contact = contact;
    }

    @Override
    public String toString() {
        return "RiderDetails{" +
                "Name='" + Name + '\'' +
                ", Id='" + Id + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact=" + Contact +
                '}';
    }
}
