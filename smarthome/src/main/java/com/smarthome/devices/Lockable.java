// Same package as the other device capabilities.
package com.smarthome.devices;

// A CAPABILITY: devices that lock and unlock (doors, windows, a safe).
// Every Lockable is also a Device. A lock is NOT Switchable: "turn on the
// front door" means nothing, so it is never forced to pretend.
//
// The CONTRACT (checked by LockableContractTest):
//   - lock() and unlock() always succeed: no exceptions, no refusals.
//   - After lock(), isLocked() is true. After unlock(), isLocked() is false.
public interface Lockable extends Device {

    // Lock it.
    // PROMISE: never refuses; afterwards isLocked() is true.
    void lock();

    // Unlock it.
    // PROMISE: never refuses; afterwards isLocked() is false.
    void unlock();

    // Report whether it is currently locked.
    // PROMISE: reflects the most recent lock()/unlock().
    boolean isLocked();
}
