// Shared switching code lives beside the Switchable contract it fulfils.
package com.smarthome.devices;

// The parts every SWITCHABLE device shares (a name, an on/off state, and the
// four methods around them), written once. Lessons 02-07 called this class
// AbstractDevice; since Lesson 08 not every device switches, so the name now
// says exactly what it is. abstract: status() is left for each subclass.
public abstract class SwitchableDevice implements Switchable {

    // The device's name, set once. private: subclasses read it via getName().
    private final String name;

    // Whether the device is on. Starts false for every device.
    private boolean on;

    // protected: only subclasses (and this package) can call it, through
    // super(name). Outside code cannot build an "abstract" device.
    protected SwitchableDevice(String name) {
        // Store the name for whichever device is being built.
        this.name = name;
    }

    // Fulfils Device.getName() once, for every switchable device.
    @Override
    public String getName() {
        // Hand back the private name.
        return name;
    }

    // Fulfils Switchable.turnOn() once, for every switchable device.
    @Override
    public void turnOn() {
        // Remember that the device is on.
        on = true;
    }

    // Fulfils Switchable.turnOff() once, for every switchable device.
    @Override
    public void turnOff() {
        // Remember that the device is off.
        on = false;
    }

    // Fulfils Switchable.isOn() once, for every switchable device.
    @Override
    public boolean isOn() {
        // Report the current state.
        return on;
    }

    // status() is deliberately NOT written here; every concrete subclass is
    // forced by the compiler to supply its own.
}
