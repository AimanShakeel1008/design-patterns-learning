// Contract tests live beside the contract they check.
package com.smarthome.devices;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless the condition is false.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// A CONTRACT TEST: the Device promises, written once, run against EVERY
// implementation. It is abstract, so JUnit never runs it on its own; each
// concrete subclass (LightContractTest, ...) supplies one kind of device and
// inherits all five tests. A new device kind earns its place in the hub by
// passing them: that is the Liskov Substitution Principle, checked by machine.
// The test methods are public so subclasses in OTHER packages (such as
// activity.LoggedDeviceContractTest) inherit and run them reliably.
public abstract class DeviceContractTest {

    // Each subclass answers: "build me one of YOUR devices with this name".
    protected abstract Device createDevice(String name);

    // Promise: a new device is off.
    @Test
    public void aNewDeviceIsOff() {
        // Freshly built.
        Device device = createDevice("Test Device");
        // Must report off.
        assertFalse(device.isOn());
    }

    // Promise: after turnOn(), isOn() is true.
    @Test
    public void turnOnMakesItOn() {
        // Freshly built...
        Device device = createDevice("Test Device");
        // ...switched on...
        device.turnOn();
        // ...reports on.
        assertTrue(device.isOn());
    }

    // Promise: after turnOff(), isOn() is false, and turnOff() never refuses.
    @Test
    public void turnOffMakesItOff() {
        // Built and switched on...
        Device device = createDevice("Test Device");
        device.turnOn();
        // ...then switched off (must not throw)...
        device.turnOff();
        // ...reports off.
        assertFalse(device.isOn());
    }

    // Promise: the name is the one it was created with.
    @Test
    public void nameIsTheOneGivenAtCreation() {
        // Built with a specific name.
        Device device = createDevice("Porch");
        // Reports exactly that name.
        assertEquals("Porch", device.getName());
    }

    // Promise: status() starts with "name: ", whether on or off.
    @Test
    public void statusStartsWithTheName() {
        // Built with a specific name.
        Device device = createDevice("Porch");
        // Off: the status line still begins with the name.
        assertTrue(device.status().startsWith("Porch: "));
        // On: same promise.
        device.turnOn();
        assertTrue(device.status().startsWith("Porch: "));
    }
}
