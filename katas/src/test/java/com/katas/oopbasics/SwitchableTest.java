package com.katas.oopbasics;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Proves that programming to the Switchable CONTRACT works for two different
// concrete classes, and that each one toggles its own state correctly.
public class SwitchableTest {

    @Test
    void a_lamp_honours_the_switchable_contract() {
        // Note the TYPE is the interface, not the class: we test the contract.
        Switchable lamp = new Lamp();
        // A brand-new lamp starts off.
        assertFalse(lamp.isOn());
        // Switching on flips its hidden state to true.
        lamp.switchOn();
        assertTrue(lamp.isOn());
        // Switching off flips it back.
        lamp.switchOff();
        assertFalse(lamp.isOn());
    }

    @Test
    void a_fan_honours_the_same_contract_differently() {
        // Same interface type, a different implementation underneath.
        Switchable fan = new Fan();
        // A new fan is not spinning.
        assertFalse(fan.isOn());
        // Switching on starts the blades.
        fan.switchOn();
        assertTrue(fan.isOn());
    }
}
