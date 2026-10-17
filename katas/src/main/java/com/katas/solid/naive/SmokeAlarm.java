// WRONG WAY: a smoke alarm forced to sign the fat HomeDevice contract.
package com.katas.solid.naive;

// Keeps the alarm-related promises, fakes or refuses the rest.
public class SmokeAlarm implements HomeDevice {

    // The room it protects.
    private final String name;

    // Whether smoke is currently detected.
    private final boolean smoke;

    // Build an alarm with its room and current reading.
    public SmokeAlarm(String name, boolean smoke) {
        // Store the name.
        this.name = name;
        // Store the reading.
        this.smoke = smoke;
    }

    // Real.
    @Override
    public String name() {
        // Hand back the name.
        return name;
    }

    // The text tag the security ladder checks.
    @Override
    public String type() {
        // "smoke", spelled exactly as the ladder expects.
        return "smoke";
    }

    // FAKED: an alarm has no lock, so it pretends "not locked".
    @Override
    public boolean isLocked() {
        // A meaningless answer forced by the fat interface.
        return false;
    }

    // Real.
    @Override
    public boolean smokeDetected() {
        // Report the reading.
        return smoke;
    }

    // REFUSED: an alarm must never be switched by anyone.
    @Override
    public void turnOn() {
        // Refused bequest.
        throw new UnsupportedOperationException("Alarms cannot be switched");
    }

    // REFUSED.
    @Override
    public void turnOff() {
        // Refused bequest.
        throw new UnsupportedOperationException("Alarms cannot be switched");
    }
}
