// Same package as the Switchable contract.
package com.smarthome.devices;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Fails unless the condition is false.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// The Switchable promises, ON TOP OF the base Device promises it inherits.
// A subclass that extends this runs both sets: 2 base + 4 switching tests.
public abstract class SwitchableContractTest extends DeviceContractTest {

    // Each subclass answers: "build me one of YOUR switchable devices".
    protected abstract Switchable createSwitchable(String name);

    // The base tests need a Device; a Switchable is one, so reuse the factory.
    @Override
    protected Device createDevice(String name) {
        // Every Switchable is also a Device.
        return createSwitchable(name);
    }

    // Promise: a new switchable device is off.
    @Test
    public void aNewSwitchableIsOff() {
        // Freshly built: must report off.
        assertFalse(createSwitchable("Test").isOn());
    }

    // Promise: after turnOn(), isOn() is true.
    @Test
    public void turnOnMakesItOn() {
        // Built...
        Switchable device = createSwitchable("Test");
        // ...switched on...
        device.turnOn();
        // ...reports on.
        assertTrue(device.isOn());
    }

    // Promise: after turnOff(), isOn() is false, and turnOff() never refuses.
    @Test
    public void turnOffMakesItOff() {
        // Built and switched on...
        Switchable device = createSwitchable("Test");
        device.turnOn();
        // ...then switched off (must not throw)...
        device.turnOff();
        // ...reports off.
        assertFalse(device.isOn());
    }

    // Promise: the status still starts with the name when the device is on.
    @Test
    public void statusStillStartsWithTheNameWhenOn() {
        // Built and switched on.
        Switchable device = createSwitchable("Porch");
        device.turnOn();
        // Same promise as the base contract, in the "on" state.
        assertTrue(device.status().startsWith("Porch: "));
    }
}
