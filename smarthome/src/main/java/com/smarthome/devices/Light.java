// Same package as the Device contract and its shared parent.
package com.smarthome.devices;

// The simplest device. "extends AbstractDevice": a Light IS an
// AbstractDevice, so it inherits the name, the on/off state, and
// getName/turnOn/turnOff/isOn. It still IS a Device too, through its parent.
public class Light extends AbstractDevice {

    // Build a light with its name.
    public Light(String name) {
        // Hand the name to AbstractDevice's constructor, which stores it.
        super(name);
    }

    // The one thing a light must say for itself.
    @Override
    public String status() {
        // Inherited getName() and isOn() replace the old private fields;
        // e.g. "Living Room Light: ON".
        return getName() + ": " + (isOn() ? "ON" : "OFF");
    }
}
