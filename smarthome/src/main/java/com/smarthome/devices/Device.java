// Device-related code lives in its own package, grouped by responsibility.
package com.smarthome.devices;

// THE hub's base contract: what EVERY device, of any kind, can promise.
// Since Lesson 08 it is deliberately SMALL. Switching, dimming and locking
// are separate capabilities (Switchable, Dimmable, Lockable), because not
// every device can do them: a smoke alarm must never be switched off, and
// "turning on" a door lock means nothing. A fat contract forced such devices
// to refuse promises (Lesson 07); small contracts let each promise only what
// it can keep (the Interface Segregation Principle).
//
// The CONTRACT (checked by DeviceContractTest for every implementation):
//   - getName() returns the name the device was created with, never changing.
//   - status() always starts with getName() + ": ".
public interface Device {

    // Every device has a human-readable name, e.g. "Living Room Light".
    // PROMISE: the same name it was created with, for its whole life.
    String getName();

    // Every device can summarise itself in one line for the hub to print.
    // PROMISE: starts with getName() + ": ".
    String status();
}
