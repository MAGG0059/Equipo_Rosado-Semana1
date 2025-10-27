package edu.dosw;

import edu.dosw.model.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {

    @Test
    void testClient() {
        Client client = new Client(1, "Juan Pérez", "Calle 123, Ciudad");

        assertEquals(1, client.getIdClient());
        assertEquals("Juan Pérez", client.getName());
        assertEquals("Calle 123, Ciudad", client.getAddress());
    }

    @Test
    void testFurnitureConcreteClass() {
        Furniture furniture = new TestFurniture(1, "Mesa", 100.0, Style.MODERN);

        assertEquals(1, furniture.getId());
        assertEquals("Mesa", furniture.getName());
        assertEquals(100.0, furniture.getPriceUnit(), 0.001);
        assertEquals(Style.MODERN, furniture.getStyle());
        assertEquals(100.0, furniture.getPrice(), 0.001);
    }

    @Test
    void testStyleEnum() {
        Style[] styles = Style.values();
        assertEquals(3, styles.length);
        assertArrayEquals(new Style[]{Style.CLASSIC, Style.MODERN, Style.RUSTIC}, styles);

        assertEquals(Style.CLASSIC, Style.valueOf("CLASSIC"));
        assertEquals(Style.MODERN, Style.valueOf("MODERN"));
        assertEquals(Style.RUSTIC, Style.valueOf("RUSTIC"));
    }

    @Test
    void testBill() {
        Client client = new Client(1, "María García", "Avenida 456");
        List<Furniture> furnitureList = Arrays.asList(
                new TestFurniture(1, "Silla", 50.0, Style.CLASSIC),
                new TestFurniture(2, "Mesa", 150.0, Style.MODERN)
        );

        Bill bill = new Bill(client, furnitureList);

        assertEquals(client, bill.getClient());
        assertEquals(furnitureList, bill.getFurnitures());
        assertEquals(200.0, bill.getSubtotal(), 0.001);
        assertEquals(200.0, bill.getTotal(), 0.001);
    }

    @Test
    void testBillWithEmptyFurnitureList() {
        Client client = new Client(1, "Carlos López", "Calle 789");
        List<Furniture> emptyList = Arrays.asList();

        Bill bill = new Bill(client, emptyList);

        assertEquals(0.0, bill.getSubtotal(), 0.001);
        assertEquals(0.0, bill.getTotal(), 0.001);
    }

    @Test
    void testBillComponentInterface() {
        Client client = new Client(1, "Test Client", "Test Address");
        List<Furniture> furnitureList = Arrays.asList(
                new TestFurniture(1, "Test Furniture", 100.0, Style.RUSTIC)
        );

        BillComponent bill = new Bill(client, furnitureList);
        assertEquals(100.0, bill.getTotal(), 0.001);
    }

    @Test
    void testBillDecorator() {
        Client client = new Client(1, "Test", "Address");
        List<Furniture> furnitureList = Arrays.asList(
                new TestFurniture(1, "Test", 100.0, Style.CLASSIC)
        );

        BillComponent originalBill = new Bill(client, furnitureList);
        BillDecorator decorator = new TestBillDecorator(originalBill);

        assertEquals(100.0, decorator.getTotal(), 0.001);
    }

    @Test
    void testDiscountDecorator() {
        Client client = new Client(1, "Cliente Descuento", "Dirección");
        List<Furniture> furnitureList = Arrays.asList(
                new TestFurniture(1, "Producto", 200.0, Style.MODERN)
        );

        BillComponent bill = new Bill(client, furnitureList);
        DiscountDecorator discountedBill = new DiscountDecorator(bill, 0.1);

        assertEquals(180.0, discountedBill.getTotal(), 0.001);
    }

    @Test
    void testEnvioDecorator() {
        Client client = new Client(1, "Cliente Envío", "Dirección");
        List<Furniture> furnitureList = Arrays.asList(
                new TestFurniture(1, "Producto", 100.0, Style.RUSTIC)
        );

        BillComponent bill = new Bill(client, furnitureList);
        EnvioDecorator envioBill = new EnvioDecorator(bill, 25.0);

        assertEquals(125.0, envioBill.getTotal(), 0.001);
        assertEquals(25.0, envioBill.getCostoEnvio(), 0.001);
    }

    @Test
    void testIVADecorator() {
        Client client = new Client(1, "Cliente IVA", "Dirección");
        List<Furniture> furnitureList = Arrays.asList(
                new TestFurniture(1, "Producto", 100.0, Style.CLASSIC)
        );

        BillComponent bill = new Bill(client, furnitureList);
        IVADecorator ivaBill = new IVADecorator(bill);

        assertEquals(119.0, ivaBill.getTotal(), 0.001);
    }

    @Test
    void testMultipleDecorators() {
        Client client = new Client(1, "Cliente Complejo", "Dirección");
        List<Furniture> furnitureList = Arrays.asList(
                new TestFurniture(1, "Producto Caro", 1000.0, Style.MODERN)
        );

        BillComponent bill = new Bill(client, furnitureList);
        bill = new IVADecorator(bill);
        bill = new DiscountDecorator(bill, 0.1);
        bill = new EnvioDecorator(bill, 50.0);

        assertEquals(1121.0, bill.getTotal(), 0.001);
    }

    @Test
    void testInventory() {
        Inventory inventory = new Inventory();
        assertNotNull(inventory);
    }

    @Test
    void testFurniStore() {
        Inventory inventory = new Inventory();
        FurniStore store = new FurniStore(inventory);
        assertNotNull(store);
    }

    private static class TestFurniture extends Furniture {
        public TestFurniture(int id, String name, double priceUnit, Style style) {
            super(id, name, priceUnit, style);
        }
    }

    private static class TestBillDecorator extends BillDecorator {
        public TestBillDecorator(BillComponent wrappedBill) {
            super(wrappedBill);
        }
    }
}