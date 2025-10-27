package edu.dosw.model;

public class Client {
    private int idClient;
    private String name;
    private String email;
    private String phone;
    private String address;

    public Client(int idClient, String name, String email, String phone, String address) {
        this.idClient = idClient;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public Client(int idClient, String name, String address) {
        this.idClient = idClient;
        this.name = name;
        this.address = address;
        this.email = "";
        this.phone = "";
    }

    public int getIdClient() {
        return idClient;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}