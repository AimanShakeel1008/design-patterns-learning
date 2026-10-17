// WRONG WAY: the rotten module. It breaks all five SOLID principles at once.
package com.katas.solid.naive;

// The JDK's growable list.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// One class that (1) decides what counts as a problem, (2) words the alert,
// (3) picks and drives the delivery channel, and (4) keeps a history. Four
// reasons to change (SRP). It switches on text tags (OCP), depends on a fat
// interface (ISP), hard-codes its senders (DIP), and invites a subclass that
// refuses its job (LSP: QuietSecurityModule).
public class SecurityModule {

    // Every device, through the fat interface.
    private final List<HomeDevice> devices;

    // The delivery channel, as a magic string: "email" or "sms".
    private final String channel;

    // Every alert ever sent, kept here too (a fourth responsibility).
    private final List<String> history = new ArrayList<>();

    // Devices and channel name handed in.
    public SecurityModule(List<HomeDevice> devices, String channel) {
        // Remember the devices.
        this.devices = devices;
        // Remember the channel name.
        this.channel = channel;
    }

    // Check every device and alert about problems.
    public void checkHouse(boolean nightTime) {
        // Visit each device...
        for (HomeDevice device : devices) {
            // ...and branch on its text tag: every new kind of device edits this ladder.
            if (device.type().equals("lock") && !device.isLocked()) {
                // An unlocked door.
                send(device.name() + " is UNLOCKED", nightTime);
            } else if (device.type().equals("smoke") && device.smokeDetected()) {
                // Smoke.
                send("SMOKE at " + device.name(), nightTime);
            }
        }
    }

    // Word the alert, remember it, and deliver it, all in one place.
    private void send(String problem, boolean nightTime) {
        // RESPONSIBILITY 2: wording.
        String message = (nightTime ? "[NIGHT] " : "") + "ALERT: " + problem;
        // RESPONSIBILITY 4: history.
        history.add(message);
        // RESPONSIBILITY 3: delivery, as a ladder on a string, welded to the console.
        if (channel.equals("email")) {
            // The email "sender", hard-coded.
            System.out.println("EMAIL to owner@example.com: " + message);
        } else if (channel.equals("sms")) {
            // The SMS "sender", hard-coded.
            System.out.println("SMS to 555-0100: " + message);
        } else {
            // A typo in the channel name is only discovered at run time.
            throw new IllegalArgumentException("Unknown channel: " + channel);
        }
    }

    // Hands out the internal list itself: callers can rewrite history.
    public List<String> history() {
        // Leaks the private list (an encapsulation slip from Lesson 01).
        return history;
    }
}
