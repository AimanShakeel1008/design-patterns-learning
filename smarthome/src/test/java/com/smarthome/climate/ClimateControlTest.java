// Same package as the climate policy.
package com.smarthome.climate;

// A plain switchable device to stand in for the heating.
import com.smarthome.devices.Light;
import com.smarthome.devices.Switchable;
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

// Tests the POLICY alone. Both dependencies are injected stand-ins: a fixed
// sensor and a plain light as "heating" (any Switchable will do).
class ClimateControlTest {

    // Too cold: the heating goes on.
    @Test
    void tooColdSwitchesTheHeatingOn() {
        // Heating starts off.
        Switchable heating = new Light("Heater");
        // 15C with an 18-24C range.
        String result = new ClimateControl(new FixedSensor(15), heating, 18, 24).regulate();
        // On, and described.
        assertTrue(heating.isOn());
        assertEquals("Climate: 15C in Test Room (allowed 18-24C) -> heating ON", result);
    }

    // Too hot: the heating goes off.
    @Test
    void tooHotSwitchesTheHeatingOff() {
        // Heating starts on.
        Switchable heating = new Light("Heater");
        heating.turnOn();
        // 26C with an 18-24C range.
        String result = new ClimateControl(new FixedSensor(26), heating, 18, 24).regulate();
        // Off, and described.
        assertFalse(heating.isOn());
        assertEquals("Climate: 26C in Test Room (allowed 18-24C) -> heating OFF", result);
    }

    // Inside the range (edges included): nothing changes.
    @Test
    void comfortableRangeChangesNothing() {
        // Heating starts off.
        Switchable heating = new Light("Heater");
        // Exactly the maximum is still comfortable.
        String result = new ClimateControl(new FixedSensor(24), heating, 18, 24).regulate();
        // Still off, and described.
        assertFalse(heating.isOn());
        assertEquals("Climate: 24C in Test Room (allowed 18-24C) -> no change", result);
    }

    // A range that cannot be satisfied is refused at construction.
    @Test
    void upsideDownRangeIsRefused() {
        // 25 to 18 is upside down.
        assertThrows(IllegalArgumentException.class,
                () -> new ClimateControl(new FixedSensor(20), new Light("Heater"), 25, 18));
    }
}
