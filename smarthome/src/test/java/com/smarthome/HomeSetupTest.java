// Same package as HomeSetup (the composition root, since Lesson 09).
package com.smarthome;

// The log the setup wires devices to, and the contracts.
import com.smarthome.activity.ActivityLog;
import com.smarthome.devices.Device;
import com.smarthome.devices.Lockable;
import com.smarthome.devices.Switchable;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the expected values.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Proves the setup's one job: the right devices, grouped by capability.
class HomeSetupTest {

    // The switchable devices: a light and a thermostat, both logged.
    @Test
    void createsTheSwitchableDevicesWiredToTheLog() {
        // A fresh log to wire into.
        ActivityLog log = new ActivityLog();
        // Build the switchable devices.
        List<Switchable> devices = new HomeSetup().createSwitchables(log);
        // The two expected devices, in order.
        assertEquals("Living Room Light", devices.get(0).getName());
        assertEquals("Hall Thermostat", devices.get(1).getName());
        // Switching the first one is recorded, so it really is wired to the log.
        devices.get(0).turnOn();
        assertEquals(List.of("Living Room Light: ON"), log.entries());
    }

    // The lockable devices: the front door, starting locked.
    @Test
    void createsTheLockableDevices() {
        // Build the lockable devices.
        List<Lockable> locks = new HomeSetup().createLockables();
        // One lock, the front door...
        assertEquals(1, locks.size());
        assertEquals("Front Door", locks.get(0).getName());
        // ...locked from the start.
        assertTrue(locks.get(0).isLocked());
    }

    // The other devices: an armed smoke alarm.
    @Test
    void createsTheDevicesWithOnlyTheBaseContract() {
        // Build the remaining devices.
        List<Device> others = new HomeSetup().createOtherDevices();
        // Just the smoke alarm, reporting itself through the base Device contract.
        assertEquals(1, others.size());
        assertEquals("Hallway Smoke Alarm: ARMED", others.get(0).status());
    }
}
