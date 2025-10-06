package edu.dosw.model;

public class Client {
    private int idClient;
    private String name;
    private String address;

    public Client(int idClient, String name, String address) {
        this.idClient = idClient;
        this.name = name;
        this.address = address;
    }

    public int getIdClient() {
        return idClient;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }
}
