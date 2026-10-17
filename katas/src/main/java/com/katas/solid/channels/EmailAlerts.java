// Delivery details live in their own package (DIP).
package com.katas.solid.channels;

// Depends UP on the policy's abstraction.
import com.katas.solid.AlertChannel;

// Email delivery, as one way of keeping the AlertChannel contract.
public class EmailAlerts implements AlertChannel {

    // Where to send.
    private final String address;

    // The address is chosen by the composition root.
    public EmailAlerts(String address) {
        // Remember it.
        this.address = address;
    }

    // Deliver by "email" (prints, to keep the demo predictable).
    @Override
    public void send(String message) {
        // Show what would be sent.
        System.out.println("EMAIL to " + address + ": " + message);
    }
}
