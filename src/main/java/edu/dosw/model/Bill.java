package edu.dosw.model;

import java.util.List;

public class Bill implements BillComponent {
    private Client client;
    private List<Furniture> furnitures;

    public Bill(Client cliente, List<Furniture> furnitures) {
        this.client = cliente;
        this.furnitures = furnitures;
    }

    @Override
    public double getTotal() {
        return furnitures.stream()
                .mapToDouble(Furniture::getPrice)
                .sum();
    }

    public Client getCliente() {
        return client;
    }

    public List<Furniture> getFurnitures() {
        return furnitures;
    }
}
