// Delivery details live in their own package (DIP).
package com.katas.solid.channels;

// Depends UP on the policy's abstraction.
import com.katas.solid.AlertChannel;

// SMS delivery, as another way of keeping the AlertChannel contract.
public class SmsAlerts implements AlertChannel {

    // Which phone to text.
    private final String number;

    // The number is chosen by the composition root.
    public SmsAlerts(String number) {
        // Remember it.
        this.number = number;
    }

    // Deliver by "SMS" (prints, to keep the demo predictable).
    @Override
    public void send(String message) {
        // Show what would be sent.
        System.out.println("SMS to " + number + ": " + message);
    }
}
