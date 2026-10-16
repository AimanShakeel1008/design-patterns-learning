// Shared device code lives beside the Device contract it partly fulfils.
package com.smarthome.devices;

// The parts EVERY device had copied (a name, an on/off state, and the four
// methods around them), written once. abstract: it cannot be built on its
// own, because it does not know how to describe itself; status() is left
// for each concrete device to supply.
public abstract class AbstractDevice implements Device {

    // The device's name, set once. private: subclasses read it via getName().
    private final String name;

    // Whether the device is on. Starts false for every device.
    private boolean on;

    // protected: only subclasses (and this package) can call it, through
    // super(name). Outside code cannot build an "abstract device".
    protected AbstractDevice(String name) {
        // Store the name for whichever device is being built.
        this.name = name;
    }

    // Fulfils Device.getName() once, for every device.
    @Override
    public String getName() {
        // Hand back the private name.
        return name;
    }

    // Fulfils Device.turnOn() once, for every device.
    @Override
    public void turnOn() {
        // Remember that the device is on.
        on = true;
    }

    // Fulfils Device.turnOff() once, for every device.
    @Override
    public void turnOff() {
        // Remember that the device is off.
        on = false;
    }

    // Fulfils Device.isOn() once, for every device.
    @Override
    public boolean isOn() {
        // Report the current state.
        return on;
    }

    // status() is deliberately NOT written here. Because this class is
    // abstract, it may leave a Device method unfulfilled; every concrete
    // subclass is then forced by the compiler to supply its own.
}
