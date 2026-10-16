// Device-related code lives in its own package, grouped by responsibility.
package com.smarthome.devices;

// THE hub's central contract: what every device, of any kind, promises it
// can do. The rest of the hub talks to devices ONLY through this interface,
// so new device kinds can be added without touching the code that uses them.
public interface Device {

    // Every device has a human-readable name, e.g. "Living Room Light".
    String getName();

    // Every device can be switched on (what "on" means is each device's business).
    void turnOn();

    // Every device can be switched off.
    void turnOff();

    // Every device can report whether it is currently on.
    boolean isOn();

    // Every device can summarise itself in one line for the hub to print.
    String status();
}
