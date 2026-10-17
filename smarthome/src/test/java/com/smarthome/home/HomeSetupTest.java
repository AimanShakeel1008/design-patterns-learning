// Same package as HomeSetup.
package com.smarthome.home;

// The log the setup wires devices to, and the contracts.
import com.smarthome.activity.ActivityLog;
import com.smarthome.devices.Device;
import com.smarthome.devices.Switchable;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the expected values.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    // The other devices: an armed smoke alarm and a locked front door.
    @Test
    void createsTheDevicesThatCannotSwitch() {
        // Build the non-switchable devices.
        List<Device> others = new HomeSetup().createOtherDevices();
        // Each reports itself through the base Device contract.
        assertEquals("Hallway Smoke Alarm: ARMED", others.get(0).status());
        assertEquals("Front Door: LOCKED", others.get(1).status());
    }
}
