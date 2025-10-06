package edu.dosw;

import edu.dosw.model.Furniture;
import edu.dosw.model.Style;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FurnitureTest {

    static class Chair extends Furniture {
        public Chair(int id, String name, double price, Style style) {
            super(id, name, price,style);
        }
    }

    @Test
    void testGetPrice() {
        Furniture chair = new Chair(1, "Chair", 100.0, Style.CLASSIC);
        assertEquals(100.0, chair.getPrice());
    }
}
