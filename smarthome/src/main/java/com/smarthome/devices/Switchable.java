// Same package as the base Device contract it builds on.
package com.smarthome.devices;

// A CAPABILITY: devices that can be switched on and off. "extends Device"
// means every Switchable is also a Device (it has a name and a status), so
// code that needs switching gets naming for free. Routines depend on THIS
// interface only, so they can never be handed a device that cannot switch.
//
// The CONTRACT (checked by SwitchableContractTest):
//   - A new switchable device is OFF.
//   - turnOn() and turnOff() always succeed: no exceptions, no refusals.
//   - After turnOn(), isOn() is true. After turnOff(), isOn() is false.
//   - status() still starts with getName() + ": " whether on or off.
public interface Switchable extends Device {

    // Switch on (what "on" means is each device's business).
    // PROMISE: never refuses; afterwards isOn() is true.
    void turnOn();

    // Switch off.
    // PROMISE: never refuses; afterwards isOn() is false.
    void turnOff();

    // Report whether it is currently on.
    // PROMISE: reflects the most recent turnOn()/turnOff(); false when new.
    boolean isOn();
}
