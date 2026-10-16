// Same package as the classes under test.
package com.katas.oopbasics;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check for the describe() text.
import static org.junit.jupiter.api.Assertions.assertEquals;
// assertFalse(x): fails unless x is false.
import static org.junit.jupiter.api.Assertions.assertFalse;
// assertTrue(x): fails unless x is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Proves both classes keep the Switchable contract, each in its own way.
class SwitchableTest {

    // A lamp starts off, turns on, and describes itself as a lamp.
    @Test
    void lampKeepsTheContract() {
        // Hold the lamp through the CONTRACT type, as client code would.
        Switchable lamp = new Lamp();
        // A brand-new lamp is off (its boolean field starts false).
        assertFalse(lamp.isOn());
        // Switch it on through the interface.
        lamp.turnOn();
        // Now it reports being on...
        assertTrue(lamp.isOn());
        // ...and describes itself in lamp words.
        assertEquals("Lamp is glowing", lamp.describe());
    }

    // A fan keeps the same contract with different words, and can turn off.
    @Test
    void fanKeepsTheContract() {
        // Again typed as the contract, not as Fan.
        Switchable fan = new Fan();
        // On first...
        fan.turnOn();
        // ...then off again.
        fan.turnOff();
        // It reports being off...
        assertFalse(fan.isOn());
        // ...and describes itself in fan words.
        assertEquals("Fan is still", fan.describe());
    }

    // One piece of code drives every implementation: the reward of the contract.
    @Test
    void oneLoopDrivesEveryImplementation() {
        // Two different classes in one array typed by the interface.
        Switchable[] things = { new Lamp(), new Fan() };
        // The loop never names Lamp or Fan.
        for (Switchable thing : things) {
            // Same call for each...
            thing.turnOn();
            // ...and each must honour the promise that it is now on.
            assertTrue(thing.isOn());
        }
    }
}
