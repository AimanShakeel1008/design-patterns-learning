// Test package mirrors the devices package so it can see the classes.
package com.smarthome.devices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Proves the hub's first two devices honour the Device contract and guard their
// own state. Running `mvn test` from smarthome/ is now a real regression check.
public class DeviceTest {

    @Test
    void a_light_turns_on_and_off_through_the_contract() {
        // Hold the concrete Light behind the Device interface: test the contract.
        Device light = new Light("Living Room Light");
        // Its name comes back exactly as given.
        assertEquals("Living Room Light", light.name());
        // A new light starts off.
        assertFalse(light.isOn());
        // Turning on flips its state and shows in the status line.
        light.turnOn();
        assertTrue(light.isOn());
        assertEquals("Living Room Light is ON", light.status());
    }

    @Test
    void a_thermostat_is_a_device_too_but_carries_extra_state() {
        // A different implementation behind the SAME Device type.
        Thermostat t = new Thermostat("Hall Thermostat", 21);
        // Shared contract behaviour works.
        assertFalse(t.isOn());
        t.turnOn();
        assertTrue(t.isOn());
        // Plus its own extra, guarded behaviour that Device does not know about.
        assertEquals(21, t.target());
        t.setTarget(23);
        assertEquals(23, t.target());
    }

    @Test
    void a_thermostat_refuses_an_absurd_target() {
        Thermostat t = new Thermostat("Hall Thermostat", 21);
        // The range invariant is defended: 99C is rejected.
        assertThrows(IllegalArgumentException.class, () -> t.setTarget(99));
    }

    @Test
    void a_device_must_have_a_name() {
        // The name invariant is guarded at the constructor door.
        assertThrows(IllegalArgumentException.class, () -> new Light(" "));
    }
}
