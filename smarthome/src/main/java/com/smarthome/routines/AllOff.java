// One routine: the "away" mode.
package com.smarthome.routines;

// The only capability this routine uses.
import com.smarthome.devices.Switchable;

// The JDK's list interface.
import java.util.List;

// Switch every switchable device off, e.g. when everyone leaves the house.
// It is never given the smoke alarm, so it can never try to silence it.
public class AllOff implements Routine {

    // Turn off whatever is on.
    @Override
    public void applyTo(List<Switchable> devices) {
        // Visit every switchable device...
        for (Switchable device : devices) {
            // ...and switch it off only if needed.
            if (device.isOn()) {
                // Each device turns off in its own way.
                device.turnOff();
            }
        }
    }
}
