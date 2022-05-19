package model;

public class Rider {
    private String Name;
    private String Id;
    private String Address;
    private int Contact;
    private String Email;

    public Rider() {
    }

    public Rider(String name, String id, String address, int contact, String email) {
        Name = name;
        Id = id;
        Address = address;
        Contact = contact;
        Email = email;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "Rider{" +
                "Name='" + Name + '\'' +
                ", Id='" + Id + '\'' +
                ", Address='" + Address + '\'' +
                ", Contact=" + Contact +
                ", Email='" + Email + '\'' +
                '}';
    }
}
