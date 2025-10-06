package edu.dosw;

import edu.dosw.model.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class IVADecoratorTest {

    @Test
    public void testIVADecoratorAdds19Percent() {
        Client client = new Client(1, "Alice", "Street 123");
        Furniture chair = new Furniture(1, "Chair", 100.0, Style.MODERN) {};
        Furniture table = new Furniture(2, "Table", 200.0, Style.CLASSIC) {};

        BillComponent bill = new Bill(client, List.of(chair, table));
        BillComponent billWithIVA = new IVADecorator(bill);

        assertEquals(357.0, billWithIVA.getTotal(), 0.001);
    }
}
