// Same package as the Demeter kata.
package com.katas.demeter;

// The reaching-through version, kept frozen to show the pain.
import com.katas.demeter.naive.Customer;
import com.katas.demeter.naive.Paperboy;
// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Pins the naive bug: a change to the customer's insides silently broke a caller.
class TrainWreckTest {

    // The jar was added inside Customer; the reaching paperboy never heard about it.
    @Test
    void aPaperboyWrittenBeforeTheJarMissesTheMoney() {
        // 1 EUR in the wallet, 10 in the jar: easily enough for a 2 EUR paper.
        Customer customer = new Customer(1, 10);
        // Yet nothing is collected, because the paperboy only reaches into the wallet.
        assertEquals(0, new Paperboy().collect(customer, 2));
        // The jar's money is untouched.
        assertEquals(10, customer.getCoinJar().getCash());
    }
}
