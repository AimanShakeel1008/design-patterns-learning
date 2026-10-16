// The contract for "tell someone something", whatever the channel.
package com.katas.coupling;

// Checkout depends on THIS interface, not on the console, so the channel can
// change (console, email, a test recorder) without touching Checkout.
public interface Notifier {

    // Deliver one message.
    void send(String message);
}
