// Same package as Checkout and the test helper.
package com.katas.coupling;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the expected messages.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves Checkout's one job, using a stand-in channel instead of the console.
class CheckoutTest {

    // Placing an order sends exactly one message with the right numbers.
    @Test
    void placingAnOrderSendsOneMessage() {
        // The recorder stands in for any real channel.
        RecordingNotifier notifier = new RecordingNotifier();
        // Checkout cannot tell it is talking to a recorder.
        Checkout checkout = new Checkout(notifier);
        // An order of 2 x 9 + 1 x 3.
        Order order = new Order();
        order.add(new Pizza("Margherita", 9), 2);
        order.add(new Drink("Cola", 3), 1);
        // Place it.
        checkout.place(order);
        // Exactly one message, with count 3 and total 21.
        assertEquals(List.of("Order placed: 3 items, total 21"), notifier.messages());
    }

    // An empty order is still announced, with zeros.
    @Test
    void emptyOrderIsAnnouncedWithZeros() {
        // A fresh recorder.
        RecordingNotifier notifier = new RecordingNotifier();
        // Place an order with nothing in it.
        new Checkout(notifier).place(new Order());
        // Zero items, zero total.
        assertEquals(List.of("Order placed: 0 items, total 0"), notifier.messages());
    }
}
