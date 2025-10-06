package edu.dosw;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.dosw.model.*;
import java.util.Arrays;
import java.util.List;

public class ApplicationTest {

    @Test
    void testFacturaBasica() {
        Client client = new Client();
        Furniture mueble1 = new Furniture() { public double getPrice() { return 100.0; } };
        Furniture mueble2 = new Furniture() { public double getPrice() { return 200.0; } };
        List<Furniture> muebles = Arrays.asList(mueble1, mueble2);
        Bill factura = new Bill(client, muebles);
        assertEquals(300.0, factura.getTotal());
    }
    @Test
    void testFacturaConIVA() {
        Client client = new Client();
        Furniture mueble = new Furniture() { public double getPrice() { return 100.0; } };
        Bill facturaBase = new Bill(client, Arrays.asList(mueble));
        IVADecorator facturaConIVA = new IVADecorator(facturaBase);
        assertEquals(119.0, facturaConIVA.getTotal());
    }
    @Test
    void testFacturaConDescuento() {
        Client client = new Client();
        Furniture mueble = new Furniture() { public double getPrice() { return 100.0; } };
        Bill facturaBase = new Bill(client, Arrays.asList(mueble));
        DiscountDecorator facturaConDescuento = new DiscountDecorator(facturaBase, 0.2);
        assertEquals(80.0, facturaConDescuento.getTotal());
    }
    @Test
    void testFacturaConEnvio() {
        Client client = new Client();
        Furniture mueble = new Furniture() { public double getPrice() { return 100.0; } };
        Bill facturaBase = new Bill(client, Arrays.asList(mueble));
        ShippingDecorator facturaConEnvio = new ShippingDecorator(facturaBase, 50.0);
        assertEquals(150.0, facturaConEnvio.getTotal());
    }
    @Test
    void testFacturaCompleta() {
        Client client = new Client();
        Furniture mueble1 = new Furniture() { public double getPrice() { return 100.0; } };
        Furniture mueble2 = new Furniture() { public double getPrice() { return 200.0; } };
        Bill facturaBase = new Bill(client, Arrays.asList(mueble1, mueble2));
        BillComponent conIVA = new IVADecorator(facturaBase);
        BillComponent conDescuento = new DiscountDecorator(conIVA, 0.1);
        BillComponent conEnvio = new ShippingDecorator(conDescuento, 100.0);
        assertEquals(421.3, conEnvio.getTotal(), 0.01);
    }
    @Test
    void testFacturaSinProductos() {
        Client client = new Client();
        Bill facturaVacia = new Bill(client, Arrays.asList());
        assertEquals(0.0, facturaVacia.getTotal());
    }
    @Test
    void testFacturaConMultiplesDecorators() {
        Client client = new Client();
        Furniture mueble = new Furniture() { public double getPrice() { return 500.0; } };
        Bill facturaBase = new Bill(client, Arrays.asList(mueble));
        BillComponent facturaFinal = new ShippingDecorator(new DiscountDecorator(new IVADecorator(facturaBase), 0.15), 75.0);
        assertEquals(580.75, facturaFinal.getTotal(), 0.01);
    }
}