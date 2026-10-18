// Same package as the Demeter classes under test.
package com.katas.demeter;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves the payoff: the paperboy works however the customer keeps its money.
class PaperboyTest {

    // The wallet is short, but the jar can pay: the customer decides, the paperboy collects.
    @Test
    void collectsWhicheverWayTheCustomerChoosesToPay() {
        // 1 EUR in the wallet, 10 in the jar.
        Customer customer = new Customer(1, 10);
        // The paper costs 2 EUR, and it is collected in full.
        assertEquals(2, new Paperboy().collect(customer, 2));
        // 11 - 2 = 9 EUR left.
        assertEquals(9, customer.moneyLeft());
    }

    // A customer who cannot pay declines, and nothing is taken.
    @Test
    void aCustomerWhoCannotPayIsNotRobbed() {
        // 1 EUR in each place: neither holds 2 EUR.
        Customer customer = new Customer(1, 1);
        // Nothing collected.
        assertEquals(0, new Paperboy().collect(customer, 2));
        // Both euros are still there.
        assertEquals(2, customer.moneyLeft());
    }
}
