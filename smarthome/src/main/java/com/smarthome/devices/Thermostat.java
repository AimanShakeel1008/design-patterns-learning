package com.smarthome.devices;

// A SECOND concrete Device that shares nothing with Light in its guts — it holds
// a target temperature, not a bulb — yet honours the exact same Device contract.
// This is the proof that the hub can treat "very different things" uniformly.
public class Thermostat implements Device {

    // The thermostat's name, set once and fixed.
    private final String name;
    // Whether the thermostat is actively running (heating/cooling) or idle.
    private boolean on;
    // The temperature the thermostat is aiming for, in Celsius. Extra state that
    // Light does not have — each implementation is free to carry its own data.
    private int targetCelsius;

    public Thermostat(String name, int initialTargetCelsius) {
        // Guard the name invariant, same as the light.
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("A thermostat must have a name.");
        }
        this.name = name;
        // Start idle until switched on.
        this.on = false;
        // Store the requested starting target through the guarding setter, so the
        // range rule is enforced from the very first value.
        setTarget(initialTargetCelsius);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public void turnOn() {
        // For a thermostat, "on" means it begins actively holding its target.
        this.on = true;
    }

    @Override
    public void turnOff() {
        // Off means it stops regulating.
        this.on = false;
    }

    @Override
    public boolean isOn() {
        return this.on;
    }

    // A behaviour unique to Thermostat (not on the Device contract): changing the
    // target, guarded so the value stays inside a sane household range.
    public void setTarget(int celsius) {
        // Refuse absurd temperatures — the invariant "target stays livable."
        if (celsius < 5 || celsius > 35) {
            throw new IllegalArgumentException("Target must be between 5 and 35 C.");
        }
        this.targetCelsius = celsius;
    }

    // A read-only window onto the target, so callers can see but not bypass the guard.
    public int target() {
        return this.targetCelsius;
    }

    @Override
    public String status() {
        // Include the extra thermostat detail the light does not have.
        return this.name + " is " + (this.on ? "ON" : "OFF")
                + " (target " + this.targetCelsius + "C)";
    }
}
