// Same package as Hub.
package com.smarthome.home;

// Plain devices: the hub does not care whether they are logged.
import com.smarthome.devices.Device;
import com.smarthome.devices.Light;
import com.smarthome.devices.Thermostat;
// The "everything on" routine, used to start the house in these tests.
import com.smarthome.routines.AllOn;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the device list.
import java.util.List;

// Fails unless the condition is false.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Proves the hub's one job: operating the devices it is given.
class HubTest {

    // run hands the devices to the routine (here: switch everything on).
    @Test
    void runAppliesTheRoutineToEveryDevice() {
        // Two plain devices.
        Light light = new Light("Desk Lamp");
        Thermostat thermostat = new Thermostat("Office", 20);
        // A hub operating them.
        Hub hub = new Hub(List.of(light, thermostat));
        // Run the "everything on" routine.
        hub.run(new AllOn());
        // Both are on.
        assertTrue(light.isOn());
        assertTrue(thermostat.isOn());
    }

    // turnOff finds the device by name and switches only that one off.
    @Test
    void turnOffSwitchesOnlyTheNamedDevice() {
        // Two lights, both started.
        Device desk = new Light("Desk Lamp");
        Device porch = new Light("Porch Light");
        Hub hub = new Hub(List.of(desk, porch));
        hub.run(new AllOn());
        // Switch one off by name.
        hub.turnOff("Porch Light");
        // Only the named one changed.
        assertTrue(desk.isOn());
        assertFalse(porch.isOn());
    }

    // An unknown name is refused loudly.
    @Test
    void unknownNameIsRefused() {
        // A one-device hub.
        Hub hub = new Hub(List.of(new Light("Desk Lamp")));
        // Asking for a device that does not exist must throw.
        assertThrows(IllegalArgumentException.class, () -> hub.turnOff("Garage Light"));
    }
}
