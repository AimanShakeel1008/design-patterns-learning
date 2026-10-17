// Low-level details live in their own package.
package com.katas.dip.channels;

// Depends UP on the policy's abstraction, like EmailChannel.
import com.katas.dip.AlertChannel;

// Text messages, as another way of keeping the AlertChannel contract. Added
// without touching TemperatureMonitor.
public class SmsChannel implements AlertChannel {

    // Which phone to text; an SMS-specific detail the policy never sees.
    private final String number;

    // The number is chosen by whoever assembles the program.
    public SmsChannel(String number) {
        // Remember it.
        this.number = number;
    }

    // Translate the policy's request into SMS terms.
    @Override
    public void send(String message) {
        // Show what would be sent.
        System.out.println("SMS to " + number + ": " + message);
    }
}
