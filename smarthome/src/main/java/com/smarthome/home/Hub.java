// Code about the house as a whole lives in the "home" package.
package com.smarthome.home;

// The contract: the hub never names a concrete device class.
import com.smarthome.devices.Device;

// The JDK's list interface.
import java.util.List;

// ONE responsibility: operating the house (starting it up, switching
// devices). It answers to whoever decides how the home behaves. It does not
// know which devices exist (HomeSetup) or how anything is displayed (report).
public class Hub {

    // The devices under control. Typed by the contract.
    private final List<Device> devices;

    // The devices are handed in: the hub operates them but did not choose them.
    public Hub(List<Device> devices) {
        // Keep an unmodifiable copy, so nobody can add or remove behind our back.
        this.devices = List.copyOf(devices);
    }

    // Switch every device on.
    public void startUp() {
        // Same call for every device; each does its own thing.
        for (Device device : devices) {
            // Polymorphism at work, through the Device contract.
            device.turnOn();
        }
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
