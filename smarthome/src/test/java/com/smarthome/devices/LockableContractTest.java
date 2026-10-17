// Same package as the Lockable contract.
package com.smarthome.devices;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Fails unless the condition is false.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// The Lockable promises, ON TOP OF the base Device promises it inherits.
public abstract class LockableContractTest extends DeviceContractTest {

    // Each subclass answers: "build me one of YOUR lockable devices".
    protected abstract Lockable createLockable(String name);

    // The base tests need a Device; a Lockable is one, so reuse the factory.
    @Override
    protected Device createDevice(String name) {
        // Every Lockable is also a Device.
        return createLockable(name);
    }

    // Promise: after lock(), isLocked() is true, and lock() never refuses.
    @Test
    public void lockMakesItLocked() {
        // Built, unlocked first so the test does not depend on the start state...
        Lockable lock = createLockable("Test");
        lock.unlock();
        // ...then locked.
        lock.lock();
        // Reports locked.
        assertTrue(lock.isLocked());
    }

    // Promise: after unlock(), isLocked() is false, and unlock() never refuses.
    @Test
    public void unlockMakesItUnlocked() {
        // Built and locked first...
        Lockable lock = createLockable("Test");
        lock.lock();
        // ...then unlocked.
        lock.unlock();
        // Reports unlocked.
        assertFalse(lock.isLocked());
    }
}
