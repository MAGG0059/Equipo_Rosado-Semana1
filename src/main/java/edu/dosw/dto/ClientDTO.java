package edu.dosw.dto;

public class ClientDTO {
    private int idClient;
    private String name;
    private String address;

    public ClientDTO() {}

    public ClientDTO(int idClient, String name, String address) {
        this.idClient = idClient;
        this.name = name;
        this.address = address;
    }

    public int getIdClient() { return idClient; }
    public void setIdClient(int idClient) { this.idClient = idClient; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}