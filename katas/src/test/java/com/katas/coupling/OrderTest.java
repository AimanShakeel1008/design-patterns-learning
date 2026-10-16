// Same package as the classes under test.
package com.katas.coupling;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for building a menu.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;

// Proves the small cohesive classes each do their one job correctly.
class OrderTest {

    // An order adds up its own lines.
    @Test
    void orderTotalsItsLines() {
        // A fresh order.
        Order order = new Order();
        // 2 x 9 = 18.
        order.add(new Pizza("Margherita", 9), 2);
        // 1 x 3 = 3.
        order.add(new Drink("Cola", 3), 1);
        // 2 + 1 items.
        assertEquals(3, order.itemCount());
        // 18 + 3 money.
        assertEquals(21, order.total());
    }

    // An order line can never be created with a nonsense quantity.
    @Test
    void zeroQuantityIsRefused() {
        // A fresh order.
        Order order = new Order();
        // Zero pizzas makes no sense, so the line must refuse to exist.
        assertThrows(IllegalArgumentException.class, () -> order.add(new Pizza("Margherita", 9), 0));
    }

    // The menu refuses names it does not have.
    @Test
    void menuRefusesUnknownItems() {
        // A one-item menu.
        Menu menu = new Menu(List.of(new Drink("Cola", 3)));
        // Asking for something absent must throw.
        assertThrows(IllegalArgumentException.class, () -> menu.find("Sushi"));
    }
}
