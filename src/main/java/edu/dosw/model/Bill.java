package edu.dosw.model;

import java.util.List;

public class Bill implements BillComponent {
    private Client client;
    private List<Furniture> furnitures;

    public Bill(Client client, List<Furniture> furnitures) {
        this.client = client;
        this.furnitures = furnitures;
    }

    @Override
    public double getTotal() {
        return getSubtotal();
    }

    public double getSubtotal() {
        return furnitures.stream()
                .mapToDouble(Furniture::getPrice)
                .sum();
    }

    public Client getClient() {
        return client;
    }

    public List<Furniture> getFurnitures() {
        return furnitures;
    }

}
