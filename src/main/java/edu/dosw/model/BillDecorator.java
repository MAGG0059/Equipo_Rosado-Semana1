package edu.dosw.model;

public abstract class BillDecorator implements BillComponent {
    protected BillComponent wrappedBill;

    public BillDecorator(BillComponent wrappedBill) {
        this.wrappedBill = wrappedBill;
    }

    @Override
    public double getTotal() {
        return wrappedBill.getTotal();
    }
}