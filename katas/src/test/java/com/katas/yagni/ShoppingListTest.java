// Same package as the lean printer.
package com.katas.yagni;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the items.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// The whole of today's requirement, in one test.
class ShoppingListTest {

    // One dashed line per item, in order.
    @Test
    void printsOneDashedLinePerItem() {
        // Two items in, two lines out.
        assertEquals("- milk\n- eggs\n", new ShoppingList().print(List.of("milk", "eggs")));
    }
}
