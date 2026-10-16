// Placing an order: one job, announcing it. High cohesion.
package com.katas.coupling;

// Checkout HOLDS a Notifier for its whole life (an ASSOCIATION to an
// interface: loose coupling) and only BRIEFLY USES an Order passed into one
// method call (a DEPENDENCY: the weakest link of all).
public class Checkout {

    // The channel to announce on. Typed by the interface, handed in from
    // outside, so Checkout never knows or cares which channel it is.
    private final Notifier notifier;

    // The caller chooses the channel.
    public Checkout(Notifier notifier) {
        // Remember it.
        this.notifier = notifier;
    }

    // Announce an order. The Order is only a parameter: Checkout does not
    // keep it after this method returns.
    public void place(Order order) {
        // Ask the order for its numbers and send one message.
        notifier.send("Order placed: " + order.itemCount() + " items, total " + order.total());
    }
}
