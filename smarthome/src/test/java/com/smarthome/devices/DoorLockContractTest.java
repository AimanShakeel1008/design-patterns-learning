// Same package as the contract test it extends.
package com.smarthome.devices;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Runs the base Device promises AND the Lockable promises against DoorLock
// (2 + 2 tests), plus one of its own.
class DoorLockContractTest extends LockableContractTest {

    // Supply a DoorLock; the inherited tests do the rest.
    @Override
    protected Lockable createLockable(String name) {
        // A lock with the requested name.
        return new DoorLock(name);
    }

    // Its own behaviour: a new lock starts locked, and says so.
    @Test
    void doorLockStartsLockedAndSaysSo() {
        // A brand-new lock.
        DoorLock door = new DoorLock("Front Door");
        // Locked from the start...
        assertEquals("Front Door: LOCKED", door.status());
        // ...and unlocking shows in the status.
        door.unlock();
        assertEquals("Front Door: UNLOCKED", door.status());
    }
}
