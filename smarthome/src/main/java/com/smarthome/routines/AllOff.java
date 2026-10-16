// One routine: the "away" mode.
package com.smarthome.routines;

// The device contract.
import com.smarthome.devices.Device;

// The JDK's list interface.
import java.util.List;

// Switch every device off, e.g. when everyone leaves the house.
public class AllOff implements Routine {

    // Turn off whatever is on.
    @Override
    public void applyTo(List<Device> devices) {
        // Visit every device...
        for (Device device : devices) {
            // ...and switch it off only if needed.
            if (device.isOn()) {
                // Each device turns off in its own way.
                device.turnOff();
            }
        }
    }
}
