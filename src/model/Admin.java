package model;

public class Admin {
    private String Name;
    private String AId;
    private String Address;
    private String Email;
    private String Password;
    private String RePassword;

    public Admin() {
    }

    public Admin(String name, String AId, String address, String email, String password, String rePassword) {
        Name = name;
        this.AId = AId;
        Address = address;
        Email = email;
        Password = password;
        RePassword = rePassword;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAId() {
        return AId;
    }

    public void setAId(String AId) {
        this.AId = AId;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getRePassword() {
        return RePassword;
    }

    public void setRePassword(String rePassword) {
        RePassword = rePassword;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "Name='" + Name + '\'' +
                ", AId='" + AId + '\'' +
                ", Address='" + Address + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", RePassword='" + RePassword + '\'' +
                '}';
    }
}
