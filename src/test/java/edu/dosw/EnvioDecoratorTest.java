package edu.dosw;


import edu.dosw.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class EnvioDecoratorTest {

    @Test
    public void testEnvioAddsShippingCost() {
        Client client = new Client(1, "Charlie", "Ocean Blvd");
        Furniture desk = new Furniture(1, "Desk", 250.0, Style.RUSTIC) {};

        BillComponent bill = new Bill(client, List.of(desk));
        BillComponent billWithEnvio = new EnvioDecorator(bill, 50.0);

        assertEquals(300.0, billWithEnvio.getTotal(), 0.001);
    }

    @Test
    public void testEnvioWithZeroCost() {
        Client client = new Client(1, "Charlie", "Ocean Blvd");
        Furniture desk = new Furniture(1, "Desk", 250.0, Style.RUSTIC) {};

        BillComponent bill = new Bill(client, List.of(desk));
        BillComponent billWithEnvio = new EnvioDecorator(bill, 0.0);

        assertEquals(250.0, billWithEnvio.getTotal(), 0.001);
    }
}
