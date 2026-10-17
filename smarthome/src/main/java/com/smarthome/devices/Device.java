// Device-related code lives in its own package, grouped by responsibility.
package com.smarthome.devices;

// THE hub's central contract: what every device, of any kind, promises it
// can do. The rest of the hub talks to devices ONLY through this interface,
// so new device kinds can be added without touching the code that uses them.
//
// Since Lesson 07 the promises are written down, because every class that
// implements Device must keep ALL of them (the Liskov Substitution
// Principle). DeviceContractTest checks them for every implementation.
// The CONTRACT:
//   - A new device is OFF.
//   - turnOn() and turnOff() always succeed: no exceptions, no refusals.
//   - After turnOn(), isOn() is true. After turnOff(), isOn() is false.
//   - getName() returns the name the device was created with, never changing.
//   - status() always starts with getName() + ": ".
public interface Device {

    // Every device has a human-readable name, e.g. "Living Room Light".
    // PROMISE: the same name it was created with, for its whole life.
    String getName();

    // Every device can be switched on (what "on" means is each device's business).
    // PROMISE: never refuses; afterwards isOn() is true.
    void turnOn();

    // Every device can be switched off.
    // PROMISE: never refuses; afterwards isOn() is false.
    void turnOff();

    // Every device can report whether it is currently on.
    // PROMISE: reflects the most recent turnOn()/turnOff(); false when new.
    boolean isOn();

    // Every device can summarise itself in one line for the hub to print.
    // PROMISE: starts with getName() + ": ".
    String status();
}
