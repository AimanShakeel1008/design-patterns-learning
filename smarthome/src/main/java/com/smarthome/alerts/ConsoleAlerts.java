// Alert DELIVERY details live in their own package.
package com.smarthome.alerts;

// Depends UP on the security policy's abstraction (DIP).
import com.smarthome.security.AlertChannel;

// Delivers alerts by printing them: the hub's first channel. A phone-app or
// email channel would be another class here; LockCheck would not change.
public class ConsoleAlerts implements AlertChannel {

    // Deliver by printing the message as it is.
    @Override
    public void send(String message) {
        // One line per alert.
        System.out.println(message);
    }
}
