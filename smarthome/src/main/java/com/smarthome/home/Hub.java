// Code about the house as a whole lives in the "home" package.
package com.smarthome.home;

// The base contract (every device) and two capabilities (locking, switching).
import com.smarthome.devices.Device;
import com.smarthome.devices.Lockable;
import com.smarthome.devices.Switchable;
// The contract for modes: the hub never names a concrete routine.
import com.smarthome.routines.Routine;

// The JDK's growable list, for combining the device groups.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// ONE responsibility: operating the house (running routines, switching
// devices). It does not know which devices exist (HomeSetup), which modes
// exist (routines), or how anything is displayed (report). Since Lesson 08
// it keeps switchable devices in their own typed list, so routines receive
// only devices that can keep the Switchable contract. Since Lesson 10 it
// keeps lockable devices in their own typed group too, for the security
// check. No instanceof needed: the setup hands over each capability's
// devices separately. Since Lesson 11 it can unlock a lock by name, and both
// by-name lookups share one private helper.
public class Hub {

    // The devices that can be switched: the only ones routines may touch.
    private final List<Switchable> switchables;

    // The devices that can be locked: what the security check inspects.
    private final List<Lockable> lockables;

    // Every device in the house, of any kind, for reading and reporting.
    private final List<Device> allDevices;

    // The setup hands over each capability's devices separately.
    public Hub(List<Switchable> switchables, List<Lockable> lockables, List<Device> otherDevices) {
        // An unmodifiable copy of the switchable ones.
        this.switchables = List.copyOf(switchables);
        // An unmodifiable copy of the lockable ones.
        this.lockables = List.copyOf(lockables);
        // Build the full list: switchables, then lockables, then the rest.
        List<Device> all = new ArrayList<>();
        // Every Switchable is also a Device, so it may join a List<Device>.
        all.addAll(switchables);
        // Every Lockable is also a Device.
        all.addAll(lockables);
        // The devices that only promise the base contract.
        all.addAll(otherDevices);
        // Keep an unmodifiable copy.
        this.allDevices = List.copyOf(all);
    }

    // The lockable devices, for the security check (and anyone who locks).
    public List<Lockable> lockables() {
        // Already unmodifiable, so safe to hand out.
        return lockables;
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
        // The shared lookup rule, asked for a switchable device.
        return findNamed(switchables, name, "switchable");
    }

    // Unlock one lock, found by its name. Added in Lesson 11 so callers ASK
    // the hub instead of reaching into its list by position (Law of Demeter).
    // There is no lock(name) yet, just as there is no turnOn(name): nothing
    // needs it, so it is not built (YAGNI).
    public void unlock(String name) {
        // Find it (or fail loudly), then unlock it.
        findNamed(lockables, name, "lockable").unlock();
    }

    // The ONE copy of the hub's "find a device by its exact name" rule (DRY),
    // shared by switchableNamed and unlock. A GENERIC method: <T extends Device>
    // is a type placeholder, so the same code searches a List<Switchable> and
    // hands back a Switchable, or a List<Lockable> and hands back a Lockable.
    private static <T extends Device> T findNamed(List<T> devices, String name, String kind) {
        // Check each device in turn...
        for (T device : devices) {
            // ...and return the first whose name matches (getName comes from Device).
            if (device.getName().equals(name)) {
                // Found it.
                return device;
            }
        }
        // No match: refuse loudly, naming the kind and what was asked for.
        throw new IllegalArgumentException("No " + kind + " device named: " + name);
    }
}
