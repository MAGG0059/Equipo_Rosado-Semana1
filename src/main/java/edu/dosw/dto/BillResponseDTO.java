package edu.dosw.dto;

import java.util.List;

public class BillResponseDTO {
    private int orderId;
    private ClientDTO client;
    private List<FurnitureDTO> furnitures;
    private double subtotal;
    private double total;
    private double iva;
    private double discount;
    private double shipping;

    public BillResponseDTO() {}

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public ClientDTO getClient() { return client; }
    public void setClient(ClientDTO client) { this.client = client; }

    public List<FurnitureDTO> getFurnitures() { return furnitures; }
    public void setFurnitures(List<FurnitureDTO> furnitures) { this.furnitures = furnitures; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public double getIva() { return iva; }
    public void setIva(double iva) { this.iva = iva; }

    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }

    public double getShipping() { return shipping; }
    public void setShipping(double shipping) { this.shipping = shipping; }
}