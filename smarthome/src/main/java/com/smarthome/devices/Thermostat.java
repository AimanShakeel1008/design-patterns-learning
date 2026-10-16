// Same package as the Device contract it fulfils.
package com.smarthome.devices;

// A device with extra data (a target temperature) and an invariant to guard:
// the target always stays between MIN_CELSIUS and MAX_CELSIUS.
public class Thermostat implements Device {

    // static final: one fixed value shared by every thermostat (a constant).
    // The lowest target we accept, so pipes never freeze.
    private static final int MIN_CELSIUS = 5;

    // The highest target we accept, so nobody cooks the house by typo.
    private static final int MAX_CELSIUS = 30;

    // The thermostat's name, set once and never changed.
    private final String name;

    // Whether it is regulating. Starts false, like every device.
    private boolean on;

    // The temperature it aims for. private, so only the guarded methods below
    // can change it, and the invariant always holds.
    private int targetCelsius;

    // Build a thermostat with a name and a starting target.
    public Thermostat(String name, int targetCelsius) {
        // Remember the name.
        this.name = name;
        // Reuse the guarded setter, so the constructor obeys the SAME rule as
        // every later change; one rule, written once.
        setTargetCelsius(targetCelsius);
    }

    // Change the target, but only to a sensible value.
    public void setTargetCelsius(int targetCelsius) {
        // Refuse anything outside the allowed range...
        if (targetCelsius < MIN_CELSIUS || targetCelsius > MAX_CELSIUS) {
            // ...loudly, naming the bad value and the allowed range.
            throw new IllegalArgumentException("Target must be " + MIN_CELSIUS + "-" + MAX_CELSIUS
                    + "C, got " + targetCelsius);
        }
        // The value is valid, so store it.
        this.targetCelsius = targetCelsius;
    }

    // Read the target without being able to change it from outside.
    public int getTargetCelsius() {
        // Hand back a copy of the number.
        return targetCelsius;
    }

    // Fulfils the contract: report the name.
    @Override
    public String getName() {
        // Return the private name.
        return name;
    }

    // Fulfils the contract: start regulating.
    @Override
    public void turnOn() {
        // Remember that it is active.
        on = true;
    }

    // Fulfils the contract: stop regulating.
    @Override
    public void turnOff() {
        // Remember that it is idle.
        on = false;
    }

    // Fulfils the contract: report the state.
    @Override
    public boolean isOn() {
        // Return the current state.
        return on;
    }

    // Fulfils the contract in a thermostat-specific way.
    @Override
    public String status() {
        // When on, say what it is aiming for; when off, just say OFF.
        return on ? name + ": regulating to " + targetCelsius + "C" : name + ": OFF";
    }
}
