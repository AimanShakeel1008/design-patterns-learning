package com.smarthome.devices;

// A concrete Device: a light. 'implements Device' is the compiler-checked
// promise that this class supplies every method the Device contract lists.
public class Light implements Device {

    // The light's own hidden data (encapsulation). 'final' on name means it is
    // set once in the constructor and can never change — a light does not get
    // renamed on the fly.
    private final String name;
    // Whether the light is currently on. Private, so only the light flips it,
    // and only through turnOn()/turnOff().
    private boolean on;

    // The constructor builds a valid light. Every light must have a name.
    public Light(String name) {
        // Guard against a nameless device sneaking in — a small invariant.
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("A light must have a name.");
        }
        // Store the accepted name into the object's own final field.
        this.name = name;
        // A brand-new light starts off. (boolean fields default to false anyway;
        // we set it explicitly to make the starting state obvious to a reader.)
        this.on = false;
    }

    @Override
    public String name() {
        // Hand back the name. Strings are immutable in Java, so returning it
        // cannot let a caller reach in and change our field.
        return this.name;
    }

    @Override
    public void turnOn() {
        // A light turning on means its bulb becomes lit.
        this.on = true;
    }

    @Override
    public void turnOff() {
        // Turning off darkens the bulb.
        this.on = false;
    }

    @Override
    public boolean isOn() {
        // Report the current state truthfully.
        return this.on;
    }

    @Override
    public String status() {
        // Build a one-line description the hub can print. A ternary chooses the
        // word based on state: condition ? valueIfTrue : valueIfFalse.
        return this.name + " is " + (this.on ? "ON" : "OFF");
    }
}
