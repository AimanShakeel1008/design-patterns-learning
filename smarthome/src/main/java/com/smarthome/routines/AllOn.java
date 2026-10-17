// One routine: wake the whole house.
package com.smarthome.routines;

// The only capability this routine uses.
import com.smarthome.devices.Switchable;

// The JDK's list interface.
import java.util.List;

// Switch every switchable device on. (Lesson 06 turned Hub.startUp() into
// this routine.)
public class AllOn implements Routine {

    // Turn on whatever is not already on.
    @Override
    public void applyTo(List<Switchable> devices) {
        // Visit every switchable device...
        for (Switchable device : devices) {
            // ...and switch it on only if needed, so nothing is logged twice.
            if (!device.isOn()) {
                // Each device turns on in its own way.
                device.turnOn();
            }
        }
    }
}
