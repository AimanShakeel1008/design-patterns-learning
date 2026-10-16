// One routine: wake the whole house.
package com.smarthome.routines;

// The device contract.
import com.smarthome.devices.Device;

// The JDK's list interface.
import java.util.List;

// Switch every device on. This used to be Hub.startUp(); now it is one
// routine among many, and the hub no longer hard-codes it.
public class AllOn implements Routine {

    // Turn on whatever is not already on.
    @Override
    public void applyTo(List<Device> devices) {
        // Visit every device...
        for (Device device : devices) {
            // ...and switch it on only if needed, so nothing is logged twice.
            if (!device.isOn()) {
                // Each device turns on in its own way.
                device.turnOn();
            }
        }
    }
}
