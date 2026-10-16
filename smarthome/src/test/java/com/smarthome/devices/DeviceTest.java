// Same package as the device classes under test.
package com.smarthome.devices;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless the condition is false.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Proves each device keeps the Device contract and guards its own data.
class DeviceTest {

    // A light switches on and off and reports it in its status line.
    @Test
    void lightTurnsOnAndOff() {
        // Typed as the contract, the way the hub uses it.
        Device light = new Light("Porch Light");
        // On: status says ON.
        light.turnOn();
        assertEquals("Porch Light: ON", light.status());
        // Off: isOn reports false and status says OFF.
        light.turnOff();
        assertFalse(light.isOn());
        assertEquals("Porch Light: OFF", light.status());
    }

    // A thermostat's status shows its target only while it is on.
    @Test
    void thermostatReportsItsTargetWhenOn() {
        // Typed as the contract.
        Device thermostat = new Thermostat("Hall Thermostat", 21);
        // Before switching on, it reports OFF.
        assertEquals("Hall Thermostat: OFF", thermostat.status());
        // After switching on, it reports the target.
        thermostat.turnOn();
        assertEquals("Hall Thermostat: regulating to 21C", thermostat.status());
    }

    // The constructor guard: an out-of-range thermostat can never be built.
    @Test
    void thermostatRefusesAnOutOfRangeStartingTarget() {
        // 40C is above the 30C maximum, so construction must throw.
        assertThrows(IllegalArgumentException.class, () -> new Thermostat("Attic", 40));
    }

    // The setter guard: a bad change is refused and the old value survives.
    @Test
    void thermostatRefusesAnOutOfRangeChangeAndKeepsItsTarget() {
        // Typed as Thermostat here, because setTargetCelsius is thermostat-only.
        Thermostat thermostat = new Thermostat("Hall Thermostat", 21);
        // 2C is below the 5C minimum, so the change must throw...
        assertThrows(IllegalArgumentException.class, () -> thermostat.setTargetCelsius(2));
        // ...and the target is still exactly what it was.
        assertEquals(21, thermostat.getTargetCelsius());
    }

    // One loop drives every kind of device: the payoff of the contract.
    @Test
    void everyDeviceStartsOffAndTurnsOnThroughTheContract() {
        // Two different classes, one array typed by the interface.
        Device[] devices = { new Light("Desk Lamp"), new Thermostat("Office", 20) };
        // The loop never names a concrete class.
        for (Device device : devices) {
            // Every device starts off...
            assertFalse(device.isOn());
            // ...and every device honours turnOn.
            device.turnOn();
            assertTrue(device.isOn());
        }
    }
}
