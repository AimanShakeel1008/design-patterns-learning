// Same package as the Lockable contract it fulfils.
package com.smarthome.devices;

// A door lock: Lockable (and therefore a Device), but NOT Switchable. It is
// never asked to "turn on", so it never has to pretend it can.
public class DoorLock implements Lockable {

    // The lock's name, set once.
    private final String name;

    // Whether the door is locked. A new lock starts LOCKED: the safe default.
    private boolean locked = true;

    // Build a lock with its name.
    public DoorLock(String name) {
        // Store the name.
        this.name = name;
    }

    // Keeps Device's promise: the creation-time name.
    @Override
    public String getName() {
        // Hand back the private name.
        return name;
    }

    // Keeps Lockable's promise: afterwards isLocked() is true.
    @Override
    public void lock() {
        // Remember that it is locked.
        locked = true;
    }

    // Keeps Lockable's promise: afterwards isLocked() is false.
    @Override
    public void unlock() {
        // Remember that it is unlocked.
        locked = false;
    }

    // Keeps Lockable's promise: reflects the latest lock()/unlock().
    @Override
    public boolean isLocked() {
        // Report the state.
        return locked;
    }

    // Keeps Device's promise: starts with the name.
    @Override
    public String status() {
        // e.g. "Front Door: LOCKED".
        return name + ": " + (locked ? "LOCKED" : "UNLOCKED");
    }
}
