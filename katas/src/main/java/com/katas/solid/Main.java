// The runnable demo for Lesson 10's kata, and its composition root.
package com.katas.solid;

// The concrete delivery details: known only here.
import com.katas.solid.channels.EmailAlerts;
import com.katas.solid.channels.SmsAlerts;

// List.of to build device lists.
import java.util.List;

// Runs the same house (an unlocked front door, smoke in the kitchen) through
// the rotten module and through the refactored one.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // ROTTEN: the fat-interface devices.
        List<com.katas.solid.naive.HomeDevice> rottenHouse = List.of(
                // An unlocked front door.
                new com.katas.solid.naive.DoorLock("Front Door", false),
                // Smoke in the kitchen.
                new com.katas.solid.naive.SmokeAlarm("Kitchen", true));
        // By day: both problems are emailed.
        new com.katas.solid.naive.SecurityModule(rottenHouse, "email").checkHouse(false);
        // At night, the "quiet" subclass refuses the whole job, smoke included.
        try {
            // The substitute breaks its parent's promise.
            new com.katas.solid.naive.QuietSecurityModule(rottenHouse, "email").checkHouse(true);
        } catch (UnsupportedOperationException e) {
            // Caught: and the smoke went unreported.
            System.out.println("Naive quiet module refused: " + e.getMessage());
        }

        // REFACTORED: small-role devices, injected collaborators.
        List<Monitored> house = List.of(new DoorLock("Front Door", false), new SmokeAlarm("Kitchen", true));
        // One formatter, shared.
        AlertFormatter formatter = new AlertFormatter();
        // At night, quiet hours ON, by SMS: the door waits, the smoke does not.
        new SecurityCheck(house, new SmsAlerts("555-0100"), formatter, true).run(true);
        // By day, by email: both problems are reported.
        new SecurityCheck(house, new EmailAlerts("owner@example.com"), formatter, true).run(false);
    }
}
