// Same package as the device classes under test.
package com.smarthome.devices;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless the condition is false.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Proves the shared parent works for every subclass, and that calls through
// the parent type still run each subclass's own status(). (This was
// AbstractDeviceTest until Lesson 08 renamed the class it tests.)
class SwitchableDeviceTest {

    // The inherited switching code behaves identically for every subclass.
    @Test
    void inheritedSwitchingWorksForEverySubclass() {
        // Two different subclasses, held through the shared parent type.
        SwitchableDevice[] devices = { new Light("Hall Light"), new Thermostat("Hall", 21) };
        // Every device must run the SAME inherited code...
        for (SwitchableDevice device : devices) {
            // ...on...
            device.turnOn();
            assertTrue(device.isOn());
            // ...and off again.
            device.turnOff();
            assertFalse(device.isOn());
        }
    }

    // Declared as the parent, the call still runs the child's override.
    @Test
    void callThroughTheParentTypeRunsTheSubclassStatus() {
        // Declared type SwitchableDevice; actual object Thermostat.
        SwitchableDevice device = new Thermostat("Hall", 21);
        // Inherited turnOn() from the parent...
        device.turnOn();
        // ...then Thermostat's own status(), chosen at run time.
        assertEquals("Hall: regulating to 21C", device.status());
    }
}
