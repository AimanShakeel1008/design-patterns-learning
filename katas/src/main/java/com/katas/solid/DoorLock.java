// A lock that promises only what a lock can keep.
package com.katas.solid;

// Signs the small Monitored role. No smoke to fake and no turnOn to refuse,
// so it keeps every promise it makes (LSP).
public class DoorLock implements Monitored {

    // The door's name.
    private final String name;

    // Whether it is locked.
    private boolean locked;

    // Build a lock with its name and starting state.
    public DoorLock(String name, boolean locked) {
        // Store the name.
        this.name = name;
        // Store the state.
        this.locked = locked;
    }

    // Lock it (a lock's own behaviour, outside the security role).
    public void lock() {
        // Remember it is locked.
        locked = true;
    }

    // The name, for messages.
    @Override
    public String name() {
        // Hand back the name.
        return name;
    }

    // An unlocked door needs attention.
    @Override
    public boolean needsAttention() {
        // Problem exactly when not locked.
        return !locked;
    }

    // An unlocked door can wait until morning.
    @Override
    public boolean isUrgent() {
        // Not urgent.
        return false;
    }

    // How the lock describes its problem.
    @Override
    public String problem() {
        // e.g. "Front Door is UNLOCKED".
        return name + " is UNLOCKED";
    }
}
