// Same package as the security policy.
package com.smarthome.security;

// Real locks, used through the Lockable role.
import com.smarthome.devices.DoorLock;
import com.smarthome.devices.Lockable;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for inputs and expected values.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests the security policy alone, with a recording channel injected.
class LockCheckTest {

    // Every unlocked lock produces one alert, in order; locked ones produce none.
    @Test
    void alertsForEachUnlockedLock() {
        // Three locks: two left open, one locked.
        Lockable front = new DoorLock("Front Door");
        Lockable back = new DoorLock("Back Door");
        Lockable garage = new DoorLock("Garage");
        front.unlock();
        garage.unlock();
        // The test channel.
        RecordingAlerts alerts = new RecordingAlerts();
        // Run the check once.
        List<String> sent = new LockCheck(List.of(front, back, garage), alerts).run();
        // Two alerts, delivered and returned.
        assertEquals(List.of("ALERT: Front Door is UNLOCKED", "ALERT: Garage is UNLOCKED"), alerts.sent());
        assertEquals(alerts.sent(), sent);
    }

    // A fully locked house sends nothing.
    @Test
    void lockedHouseSendsNothing() {
        // New locks start locked.
        RecordingAlerts alerts = new RecordingAlerts();
        // Run the check.
        new LockCheck(List.of(new DoorLock("Front Door")), alerts).run();
        // Silence.
        assertEquals(List.of(), alerts.sent());
    }
}
