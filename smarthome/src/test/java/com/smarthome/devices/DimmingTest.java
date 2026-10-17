// Same package as Light and the Dimmable contract.
package com.smarthome.devices;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;

// Proves the Dimmable capability of Light, used through the Dimmable contract.
class DimmingTest {

    // A new light is at full brightness, and its status is unchanged ("ON").
    @Test
    void newLightIsAtFullBrightness() {
        // A light, switched on.
        Light light = new Light("Desk Lamp");
        light.turnOn();
        // Full brightness...
        assertEquals(100, light.getBrightness());
        // ...so the status reads exactly as it did before dimming existed.
        assertEquals("Desk Lamp: ON", light.status());
    }

    // A dimmed light says how dim it is.
    @Test
    void dimmedLightShowsItsBrightness() {
        // A light, switched on.
        Light light = new Light("Desk Lamp");
        light.turnOn();
        // Used ONLY through the Dimmable role.
        Dimmable dimmer = light;
        dimmer.setBrightness(40);
        // The status shows the percentage.
        assertEquals("Desk Lamp: ON at 40%", light.status());
    }

    // Out-of-range brightness is refused, and the brightness stays as it was.
    @Test
    void outOfRangeBrightnessIsRefusedAndNothingChanges() {
        // A light at full brightness.
        Light light = new Light("Desk Lamp");
        // 150% cannot exist.
        assertThrows(IllegalArgumentException.class, () -> light.setBrightness(150));
        // Still 100.
        assertEquals(100, light.getBrightness());
    }
}
