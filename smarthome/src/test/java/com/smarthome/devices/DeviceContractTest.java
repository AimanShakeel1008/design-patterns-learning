// Contract tests live beside the contracts they check.
package com.smarthome.devices;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// A CONTRACT TEST for the BASE Device promises, run against EVERY device.
// Since Lesson 08 the contract tests mirror the interfaces:
//   DeviceContractTest          (name + status: every device)
//     +- SwitchableContractTest (adds the on/off promises)
//     +- LockableContractTest   (adds the lock/unlock promises)
// Each concrete subclass supplies one kind of device and inherits every test
// for every contract that device signs. The test methods are public so
// subclasses in OTHER packages inherit and run them reliably.
public abstract class DeviceContractTest {

    // Each subclass answers: "build me one of YOUR devices with this name".
    protected abstract Device createDevice(String name);

    // Promise: the name is the one it was created with.
    @Test
    public void nameIsTheOneGivenAtCreation() {
        // Built with a specific name.
        Device device = createDevice("Porch");
        // Reports exactly that name.
        assertEquals("Porch", device.getName());
    }

    // Promise: status() starts with "name: ".
    @Test
    public void statusStartsWithTheName() {
        // Built with a specific name.
        Device device = createDevice("Porch");
        // The status line begins with the name.
        assertTrue(device.status().startsWith("Porch: "));
    }
}
