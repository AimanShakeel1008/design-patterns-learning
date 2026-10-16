// Code about the house as a whole lives in the "home" package.
package com.smarthome.home;

// The contract: the hub never names a concrete device class.
import com.smarthome.devices.Device;
// The contract for modes: the hub never names a concrete routine either.
import com.smarthome.routines.Routine;

// The JDK's list interface.
import java.util.List;

// ONE responsibility: operating the house (running routines, switching
// devices). It answers to whoever decides how the home behaves. It does not
// know which devices exist (HomeSetup), which modes exist (routines), or how
// anything is displayed (report). Since Lesson 06 it is CLOSED for new modes:
// a new mode is a new Routine class, never an edit here.
public class Hub {

    // The devices under control. Typed by the contract.
    private final List<Device> devices;

    // The devices are handed in: the hub operates them but did not choose them.
    public Hub(List<Device> devices) {
        // Keep an unmodifiable copy, so nobody can add or remove behind our back.
        this.devices = List.copyOf(devices);
    }

    // Run any routine on the whole house. No if-else on mode names: the
    // routine object itself knows what to do (polymorphism again).
    public void run(Routine routine) {
        // Hand the routine the devices; it does the rest.
        routine.applyTo(devices);
    }

    // Switch one device off, found by its name.
    public void turnOff(String name) {
        // Find it (or fail loudly), then switch it off.
        find(name).turnOff();
    }

    // The devices, for anyone who needs to read their state.
    public List<Device> devices() {
        // Already unmodifiable (List.copyOf), so safe to hand out.
        return devices;
    }

    // Look a device up by its exact name.
    private Device find(String name) {
        // Check each device in turn...
        for (Device device : devices) {
            // ...and return the first whose name matches.
            if (device.getName().equals(name)) {
                // Found it.
                return device;
            }
        }
        // No match: refuse loudly, naming what was asked for.
        throw new IllegalArgumentException("No device named: " + name);
    }
}
