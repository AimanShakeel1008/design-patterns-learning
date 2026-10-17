// Low-level details live in their own package.
package com.katas.dip.channels;

// The detail depends UP on the policy's abstraction: this import points from
// the channels package to the policy package, never the other way round.
import com.katas.dip.AlertChannel;

// Email, as one way of keeping the AlertChannel contract.
public class EmailChannel implements AlertChannel {

    // Where to send; an email-specific detail the policy never sees.
    private final String address;

    // The address is chosen by whoever assembles the program.
    public EmailChannel(String address) {
        // Remember it.
        this.address = address;
    }

    // Translate the policy's request into email terms.
    @Override
    public void send(String message) {
        // Show what would be sent (prints instead of really emailing).
        System.out.println("EMAIL to " + address + ": " + message);
    }
}
