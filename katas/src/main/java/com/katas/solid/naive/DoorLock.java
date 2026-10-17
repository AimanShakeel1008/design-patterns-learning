// WRONG WAY: a lock forced to sign the fat HomeDevice contract.
package com.katas.solid.naive;

// Keeps the lock-related promises, fakes or refuses the rest.
public class DoorLock implements HomeDevice {

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

    // Real.
    @Override
    public String name() {
        // Hand back the name.
        return name;
    }

    // The text tag the security ladder checks.
    @Override
    public String type() {
        // "lock", spelled exactly as the ladder expects.
        return "lock";
    }

    // Real.
    @Override
    public boolean isLocked() {
        // Report the state.
        return locked;
    }

    // FAKED: a lock has no smoke sensor, so it pretends "no smoke".
    @Override
    public boolean smokeDetected() {
        // A meaningless answer forced by the fat interface.
        return false;
    }

    // REFUSED: a refused bequest (Lesson 07).
    @Override
    public void turnOn() {
        // Locks do not turn on.
        throw new UnsupportedOperationException("Locks cannot be turned on");
    }

    // REFUSED: another refused bequest.
    @Override
    public void turnOff() {
        // Locks do not turn off.
        throw new UnsupportedOperationException("Locks cannot be turned off");
    }
}
