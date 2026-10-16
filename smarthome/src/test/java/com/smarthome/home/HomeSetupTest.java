// Same package as HomeSetup.
package com.smarthome.home;

// The log the setup wires devices to, and the device contract.
import com.smarthome.activity.ActivityLog;
import com.smarthome.devices.Device;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the expected values.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves the setup's one job: the right devices, wired to the given log.
class HomeSetupTest {

    // The standard house has a light and a thermostat, both logged.
    @Test
    void createsTheStandardDevicesWiredToTheLog() {
        // A fresh log to wire into.
        ActivityLog log = new ActivityLog();
        // Build the house.
        List<Device> devices = new HomeSetup().createDevices(log);
        // The two expected devices, in order.
        assertEquals("Living Room Light", devices.get(0).getName());
        assertEquals("Hall Thermostat", devices.get(1).getName());
        // Switching the first one is recorded, so it really is wired to the log.
        devices.get(0).turnOn();
        assertEquals(List.of("Living Room Light: ON"), log.entries());
    }
}
