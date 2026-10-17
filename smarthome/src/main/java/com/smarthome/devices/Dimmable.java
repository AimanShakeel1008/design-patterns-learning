// Same package as the other device capabilities.
package com.smarthome.devices;

// A CAPABILITY: things whose brightness can be set. It does not extend
// Device on purpose: dimming is an ADD-ON that a class combines with other
// contracts (a Light is Switchable AND Dimmable). Code that only dims can
// ask for exactly this, and nothing more.
//
// The CONTRACT:
//   - Brightness is a percentage from 0 to 100.
//   - setBrightness(outside 0..100) throws IllegalArgumentException and
//     leaves the brightness unchanged.
public interface Dimmable {

    // Set the brightness percentage.
    // PRECONDITION: 0 <= percent <= 100.
    void setBrightness(int percent);

    // Read the brightness percentage.
    // PROMISE: always between 0 and 100.
    int getBrightness();
}
