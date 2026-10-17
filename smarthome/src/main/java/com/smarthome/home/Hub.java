// Code about the house as a whole lives in the "home" package.
package com.smarthome.home;

// The base contract (every device) and one capability (switching).
import com.smarthome.devices.Device;
import com.smarthome.devices.Switchable;
// The contract for modes: the hub never names a concrete routine.
import com.smarthome.routines.Routine;

// The JDK's growable list, for combining the two lists.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// ONE responsibility: operating the house (running routines, switching
// devices). It does not know which devices exist (HomeSetup), which modes
// exist (routines), or how anything is displayed (report). Since Lesson 08
// it keeps switchable devices in their own typed list, so routines receive
// only devices that can keep the Switchable contract. No instanceof needed:
// the setup hands over each capability's devices separately.
public class Hub {

    // The devices that can be switched: the only ones routines may touch.
    private final List<Switchable> switchables;

    // Every device in the house, switchable or not, for reading and reporting.
    private final List<Device> allDevices;

    // The setup hands over switchable devices and all the others separately.
    public Hub(List<Switchable> switchables, List<Device> otherDevices) {
        // An unmodifiable copy of the switchable ones.
        this.switchables = List.copyOf(switchables);
        // Build the full list: switchables first, then the rest.
        List<Device> all = new ArrayList<>();
        // Every Switchable is also a Device, so it may join a List<Device>.
        all.addAll(switchables);
        // The devices that only promise the base contract.
        all.addAll(otherDevices);
        // Keep an unmodifiable copy.
        this.allDevices = List.copyOf(all);
    }

    // Run any routine on the switchable devices. No if-else on mode names:
    // the routine object itself knows what to do (polymorphism again).
    public void run(Routine routine) {
        // Only switchable devices are handed over.
        routine.applyTo(switchables);
    }

    // Switch one device off, found by its name among the switchable ones.
    public void turnOff(String name) {
        // Find it (or fail loudly), then switch it off.
        switchableNamed(name).turnOff();
    }

    // Every device, for anyone who needs to read names and statuses.
    public List<Device> devices() {
        // Already unmodifiable, so safe to hand out.
        return allDevices;
    }

    // The switchable devices, for anyone who needs their on/off state.
    public List<Switchable> switchables() {
        // Already unmodifiable, so safe to hand out.
        return switchables;
    }

    // Look a switchable device up by its exact name. Public since Lesson 09,
    // so the composition root can hand one device to a policy (e.g. the
    // thermostat to ClimateControl) as a Switchable, never as a concrete class.
    public Switchable switchableNamed(String name) {
        // Check each switchable device in turn...
        for (Switchable device : switchables) {
            // ...and return the first whose name matches.
            if (device.getName().equals(name)) {
                // Found it.
                return device;
            }
        }
        // No match: refuse loudly, naming what was asked for.
        throw new IllegalArgumentException("No switchable device named: " + name);
    }
}
