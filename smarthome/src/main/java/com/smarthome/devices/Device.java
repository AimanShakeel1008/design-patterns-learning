// The devices package: everything that represents a controllable thing in the
// house will live here. This is the hub's first real, permanent code.
package com.smarthome.devices;

// The Device INTERFACE: the contract every controllable thing in the house must
// honour. The hub will always talk to a Device, never to a specific Light or
// Thermostat. That is the single most load-bearing decision in the whole
// project — it lets us add new device types later without rewriting the hub.
public interface Device {

    // Every device can tell you its human-readable name ("Living Room Light").
    // A method returning a value, no body: the promise, not the how.
    String name();

    // Every device can be turned on. HOW differs wildly (a light lights, a lock
    // engages), but the promise is identical.
    void turnOn();

    // Every device can be turned off.
    void turnOff();

    // Every device can report whether it is currently on.
    boolean isOn();

    // Every device can describe its current state in one human-readable line,
    // for the hub to print. Each device fills in its own details.
    String status();
}
