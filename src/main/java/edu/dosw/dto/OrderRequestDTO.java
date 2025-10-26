package edu.dosw.dto;

import java.util.List;

public class OrderRequestDTO {
    private int clientId;
    private List<Integer> furnitureIds;
    private boolean applyDiscount;
    private boolean includeShipping;
    private double shippingCost;

    public OrderRequestDTO() {}

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    public List<Integer> getFurnitureIds() { return furnitureIds; }
    public void setFurnitureIds(List<Integer> furnitureIds) { this.furnitureIds = furnitureIds; }

    public boolean isApplyDiscount() { return applyDiscount; }
    public void setApplyDiscount(boolean applyDiscount) { this.applyDiscount = applyDiscount; }

    public boolean isIncludeShipping() { return includeShipping; }
    public void setIncludeShipping(boolean includeShipping) { this.includeShipping = includeShipping; }

    public double getShippingCost() { return shippingCost; }
    public void setShippingCost(double shippingCost) { this.shippingCost = shippingCost; }
}