package edu.dosw;

import edu.dosw.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountDecoratorTest {

    @Test
    public void testDiscountDecoratorAppliesCorrectly() {
        Client client = new Client(1, "Bob", "Main Ave");
        Furniture chair = new Furniture(1, "Chair", 100.0, Style.MODERN) {};
        Furniture table = new Furniture(2, "Table", 200.0, Style.CLASSIC) {};

        BillComponent bill = new Bill(client, List.of(chair, table));
        BillComponent discountedBill = new DiscountDecorator(bill, 0.10);

        assertEquals(270.0, discountedBill.getTotal(), 0.001);
    }

    @Test
    public void testZeroDiscountDoesNotChangeTotal() {
        Client client = new Client(1, "Bob", "Main Ave");
        Furniture table = new Furniture(2, "Table", 200.0, Style.CLASSIC) {};

        BillComponent bill = new Bill(client, List.of(table));
        BillComponent discountedBill = new DiscountDecorator(bill, 0.0);

        assertEquals(200.0, discountedBill.getTotal(), 0.001);
    }
}
