package edu.dosw.model;


public class EnvioDecorator extends BillDecorator {
    private double costoEnvio;

    public EnvioDecorator(BillComponent wrappedBill, double costoEnvio) {
        super(wrappedBill);
        this.costoEnvio = costoEnvio;
    }

    @Override
    public double getTotal() {
        return super.getTotal() + costoEnvio;
    }
}
