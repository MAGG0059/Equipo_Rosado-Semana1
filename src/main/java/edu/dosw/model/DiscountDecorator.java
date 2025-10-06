package edu.dosw.model;

public class DiscountDecorator extends BillDecorator {
    private double discountRate;

    public DiscountDecorator(BillComponent wrappedBill, double discountRate) {
        super(wrappedBill);
        this.discountRate = discountRate;
    }

    @Override
    public double getTotal() {
        double subtotal = super.getTotal();
        return subtotal - (subtotal * discountRate);
    }
}
