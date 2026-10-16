// Same package as the open/closed classes.
package com.katas.ocp;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for inputs and expected values.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves the quoter handles existing, later-added and test-invented methods
// with no change to its own code.
class ShippingQuoterTest {

    // The quoter under test, shared by every test (it holds no state).
    private final ShippingQuoter quoter = new ShippingQuoter();

    // An original method: 10 + 5 x 2 = 20, two days.
    @Test
    void quotesAnOriginalMethod() {
        // Ask the quoter about Express.
        assertEquals("Express: 20 (2-day delivery)", quoter.quote(new ExpressShipping(), 5));
    }

    // The method added later: 15 + 5 x 4 = 35, one day, with no quoter edit.
    @Test
    void quotesTheMethodAddedLater() {
        // Drone did not exist when ShippingQuoter was written.
        assertEquals("Drone: 35 (1-day delivery)", quoter.quote(new DroneShipping(), 5));
    }

    // A method invented inside the test folder, mixed with a real one.
    @Test
    void quotesAMethodInventedByATest() {
        // Two methods on offer: one real, one test-only.
        List<ShippingMethod> offered = List.of(new StandardShipping(), new PickupInStore());
        // Both quoted, in order.
        assertEquals(List.of("Standard: 10 (5-day delivery)", "Pickup: 0 (1-day delivery)"),
                quoter.quoteAll(offered, 5));
    }
}
