// One implementation of the Switchable contract.
package com.katas.oopbasics;

// "implements Switchable" is a promise the compiler checks: this class must
// supply all four methods the interface lists, or it will not compile.
public class Lamp implements Switchable {

    // The lamp's one piece of data. private, so only Lamp can change it.
    // Java sets a new boolean field to false, so every lamp starts off.
    private boolean on;

    // @Override says "this fulfils a method the interface promised". If the
    // name or parameters were misspelled, the compiler would report an error.
    @Override
    public void turnOn() {
        // For a lamp, switching on just means remembering that it is on.
        on = true;
    }

    // Fulfils the second promise.
    @Override
    public void turnOff() {
        // Remember that it is off again.
        on = false;
    }

    // Fulfils the third promise.
    @Override
    public boolean isOn() {
        // Report the current state without exposing the field itself.
        return on;
    }

    // Fulfils the fourth promise in a LAMP-specific way.
    @Override
    public String describe() {
        // A lamp glows or is dark. A Fan answers the same question differently.
        return "Lamp is " + (on ? "glowing" : "dark");
    }
}
