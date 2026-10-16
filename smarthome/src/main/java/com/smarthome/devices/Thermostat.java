// Same package as the Device contract and its shared parent.
package com.smarthome.devices;

// A device with extra data (a target temperature) and an invariant to guard:
// the target always stays between MIN_CELSIUS and MAX_CELSIUS. The shared
// name/on-off behaviour now comes from AbstractDevice.
public class Thermostat extends AbstractDevice {

    // static final: one fixed value shared by every thermostat (a constant).
    // The lowest target we accept, so pipes never freeze.
    private static final int MIN_CELSIUS = 5;

    // The highest target we accept, so nobody cooks the house by typo.
    private static final int MAX_CELSIUS = 30;

    // The temperature it aims for. private, so only the guarded code below
    // can change it, and the invariant always holds.
    private int targetCelsius;

    // Build a thermostat with a name and a starting target.
    public Thermostat(String name, int targetCelsius) {
        // Build the AbstractDevice part first (name, on/off state).
        super(name);
        // Use the PRIVATE check, not the public setter: a constructor should
        // never call a method a subclass could override (see Lesson 02).
        this.targetCelsius = checkedTarget(targetCelsius);
    }

    // Change the target, but only to a sensible value.
    public void setTargetCelsius(int targetCelsius) {
        // The same single rule as the constructor uses.
        this.targetCelsius = checkedTarget(targetCelsius);
    }

    // Read the target without being able to change it from outside.
    public int getTargetCelsius() {
        // Hand back a copy of the number.
        return targetCelsius;
    }

    // The invariant, written once. private and static: it cannot be
    // overridden, and it needs no object, so it is safe to call anywhere,
    // including from the constructor.
    private static int checkedTarget(int targetCelsius) {
        // Refuse anything outside the allowed range...
        if (targetCelsius < MIN_CELSIUS || targetCelsius > MAX_CELSIUS) {
            // ...loudly, naming the bad value and the allowed range.
            throw new IllegalArgumentException("Target must be " + MIN_CELSIUS + "-" + MAX_CELSIUS
                    + "C, got " + targetCelsius);
        }
        // The value passed the check, so hand it back to be stored.
        return targetCelsius;
    }

    // The one Device method a thermostat must supply itself.
    @Override
    public String status() {
        // When on, say what it is aiming for; when off, just say OFF.
        return isOn() ? getName() + ": regulating to " + targetCelsius + "C" : getName() + ": OFF";
    }
}
