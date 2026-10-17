// Same package as the base Device contract.
package com.smarthome.devices;

// A smoke alarm: a Device, and NOTHING else. It is always armed and must
// never be switched off, so it does not sign Switchable, and therefore has
// no turnOff() to refuse. Lesson 07 could not add it without breaking the
// fat contract; with small contracts it keeps every promise it makes.
public class SmokeAlarm implements Device {

    // The alarm's name, set once.
    private final String name;

    // Build an alarm with its name.
    public SmokeAlarm(String name) {
        // Store the name.
        this.name = name;
    }

    // Keeps Device's promise: the creation-time name.
    @Override
    public String getName() {
        // Hand back the private name.
        return name;
    }

    // Keeps Device's promise: starts with the name.
    @Override
    public String status() {
        // Always armed, e.g. "Hallway Smoke Alarm: ARMED".
        return name + ": ARMED";
    }
}
