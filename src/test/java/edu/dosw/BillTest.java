package edu.dosw;

import edu.dosw.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class BillTest {

    @Test
    public void testBasicBill() {
        Client client = new Client(1, "John Doe", "123 Main St");
        Furniture chair = new Furniture(1, "Chair", 100.0, Style.MODERN) {};
        Furniture table = new Furniture(2, "Table", 200.0, Style.CLASSIC) {};

        Bill bill = new Bill(client, List.of(chair, table));
        assertEquals(300.0, bill.getTotal(), 0.001);
    }

    @Test
    public void testBillWithIVA() {
        Client client = new Client(1, "John Doe", "123 Main St");
        Furniture chair = new Furniture(1, "Chair", 100.0, Style.MODERN) {};
        Furniture table = new Furniture(2, "Table", 200.0, Style.CLASSIC) {};

        BillComponent bill = new IVADecorator(new Bill(client, List.of(chair, table)));
        assertEquals(357.0, bill.getTotal(), 0.001);
    }

    @Test
    public void testBillWithDiscount() {
        Client client = new Client(1, "John Doe", "123 Main St");
        Furniture chair = new Furniture(1, "Chair", 100.0, Style.MODERN) {};
        Furniture table = new Furniture(2, "Table", 200.0, Style.CLASSIC) {};

        BillComponent bill = new DiscountDecorator(new Bill(client, List.of(chair, table)), 0.10);
        assertEquals(270.0, bill.getTotal(), 0.001);
    }

    @Test
    public void testBillWithEnvio() {
        Client client = new Client(1, "John Doe", "123 Main St");
        Furniture chair = new Furniture(1, "Chair", 100.0, Style.MODERN) {};
        Furniture table = new Furniture(2, "Table", 200.0, Style.CLASSIC) {};

        BillComponent bill = new EnvioDecorator(new Bill(client, List.of(chair, table)), 50.0);
        assertEquals(350.0, bill.getTotal(), 0.001);
    }

    @Test
    public void testBillWithAllDecorators() {
        Client client = new Client(1, "John Doe", "123 Main St");
        Furniture chair = new Furniture(1, "Chair", 100.0, Style.MODERN) {};
        Furniture table = new Furniture(2, "Table", 200.0, Style.CLASSIC) {};

        BillComponent bill = new IVADecorator(
                new DiscountDecorator(
                        new EnvioDecorator(
                                new Bill(client, List.of(chair, table)),
                                50.0
                        ),
                        0.10
                )
        );

        assertEquals(374.85, bill.getTotal(), 0.01);
    }
}
