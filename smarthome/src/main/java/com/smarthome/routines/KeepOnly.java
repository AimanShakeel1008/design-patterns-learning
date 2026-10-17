// One routine: keep a chosen few devices on, everything else off.
package com.smarthome.routines;

// The only capability this routine uses (it also reads getName(), which
// every Switchable has, because Switchable extends Device).
import com.smarthome.devices.Switchable;

// The JDK's list interface.
import java.util.List;

// e.g. "Goodnight": keep only the thermostat running. Which devices to keep
// is chosen by whoever creates the routine, so one class covers many modes.
public class KeepOnly implements Routine {

    // The names of the devices that should stay (or be switched) on.
    private final List<String> namesToKeepOn;

    // The caller decides which devices stay on.
    public KeepOnly(List<String> namesToKeepOn) {
        // An unmodifiable copy, so the routine cannot change after creation.
        this.namesToKeepOn = List.copyOf(namesToKeepOn);
    }

    // Kept devices on, all others off, switching only what must change.
    @Override
    public void applyTo(List<Switchable> devices) {
        // Visit every switchable device.
        for (Switchable device : devices) {
            // Is this one on the keep list?
            boolean keep = namesToKeepOn.contains(device.getName());
            // Kept but off: switch it on.
            if (keep && !device.isOn()) {
                // On, in its own way.
                device.turnOn();
            }
            // Not kept but on: switch it off.
            if (!keep && device.isOn()) {
                // Off, in its own way.
                device.turnOff();
            }
        }
    }
}
