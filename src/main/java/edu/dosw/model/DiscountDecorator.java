package edu.dosw.model;

public class DiscountDecorator extends BillDecorator {
    private double discountPercentage;

    public DiscountDecorator(BillComponent wrappedBill, double discountPercentage) {
        super(wrappedBill);
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getTotal() {
        double subtotal = super.getTotal();
        return subtotal - (subtotal * discountPercentage);
    }
}
