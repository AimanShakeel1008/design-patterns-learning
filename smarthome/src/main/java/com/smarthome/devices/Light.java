// Same package as the Device contract it fulfils.
package com.smarthome.devices;

// The simplest device: a light that is either on or off.
public class Light implements Device {

    // final: set once in the constructor, never changed afterwards, so a
    // light cannot be renamed by accident. private: nobody else can touch it.
    private final String name;

    // Whether the light is on. Starts false, so every new light is off.
    private boolean on;

    // Build a light with its name.
    public Light(String name) {
        // this.name means "this object's own name field", as opposed to the
        // parameter that happens to share the same name.
        this.name = name;
    }

    // Fulfils the contract: report the name.
    @Override
    public String getName() {
        // Hand back the name; the field stays private.
        return name;
    }

    // Fulfils the contract: switch on.
    @Override
    public void turnOn() {
        // For a light, "on" just means remembering it is lit.
        on = true;
    }

    // Fulfils the contract: switch off.
    @Override
    public void turnOff() {
        // Remember it is dark again.
        on = false;
    }

    // Fulfils the contract: report the state.
    @Override
    public boolean isOn() {
        // Return the current state.
        return on;
    }

    // Fulfils the contract in a light-specific way.
    @Override
    public String status() {
        // e.g. "Living Room Light: ON".
        return name + ": " + (on ? "ON" : "OFF");
    }
}
