// The security POLICY: rules about keeping the house secure.
package com.smarthome.security;

// The only device capability this policy needs.
import com.smarthome.devices.Lockable;

// The JDK's growable list.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// Warns about every unlocked lock. Built SOLID from its first line:
// one job (SRP); asks each lock about itself, so new kinds of lock need no
// edit here (OCP); every Lockable keeps its contract, checked by
// LockableContractTest (LSP); needs only the small Lockable role (ISP);
// depends only on abstractions injected through the constructor (DIP).
public class LockCheck {

    // What to inspect: any lockable devices.
    private final List<Lockable> locks;

    // Where warnings go: any channel keeping the contract.
    private final AlertChannel channel;

    // CONSTRUCTOR INJECTION from the composition root.
    public LockCheck(List<Lockable> locks, AlertChannel channel) {
        // An unmodifiable copy of the locks.
        this.locks = List.copyOf(locks);
        // Remember the channel.
        this.channel = channel;
    }

    // Inspect every lock once; returns the alerts actually sent.
    public List<String> run() {
        // The alerts sent during this run.
        List<String> sent = new ArrayList<>();
        // Ask each lock about itself.
        for (Lockable lock : locks) {
            // Only unlocked ones are a problem.
            if (!lock.isLocked()) {
                // Word the alert.
                String message = "ALERT: " + lock.getName() + " is UNLOCKED";
                // Deliver it through whatever channel was injected.
                channel.send(message);
                // Report it back to the caller.
                sent.add(message);
            }
        }
        // An unmodifiable copy for the caller.
        return List.copyOf(sent);
    }
}
