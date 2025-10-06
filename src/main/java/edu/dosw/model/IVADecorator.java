package edu.dosw.model;

public class IVADecorator extends BillDecorator {
    private static final double IVA_RATE = 0.19;

    public IVADecorator(BillComponent wrappedBill) {
        super(wrappedBill);
    }

    @Override
    public double getTotal() {
        double subtotal = super.getTotal();
        return subtotal + (subtotal * IVA_RATE);
    }
}
