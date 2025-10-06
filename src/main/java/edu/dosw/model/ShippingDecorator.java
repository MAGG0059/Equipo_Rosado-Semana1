package edu.dosw.model;

public class ShippingDecorator extends BillDecorator {
    private double shippingCost;

    public ShippingDecorator(BillComponent wrappedBill, double shippingCost) {
        super(wrappedBill);
        this.shippingCost = shippingCost;
    }

    @Override
    public double getTotal() {
        return super.getTotal() + shippingCost;
    }
}
