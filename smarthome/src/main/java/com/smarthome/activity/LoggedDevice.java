// Lives with ActivityLog: it is the logging feature, not a kind of device.
package com.smarthome.activity;

// The contract this wrapper both keeps and holds.
import com.smarthome.devices.Switchable;

// Adds activity logging to ANY switchable device by composition. A
// LoggedDevice IS a Switchable (it keeps the contract, so the hub cannot tell
// the difference) and HAS a Switchable inside it (the real one), to which it
// delegates every call. Since Lesson 08 it wraps Switchable rather than
// Device, because what it logs is switching: a smoke alarm has nothing to log.
public class LoggedDevice implements Switchable {

    // The real device doing the real work. Typed by the contract, so any
    // switchable device (even another wrapper) can be wrapped.
    private final Switchable inner;

    // Where switch events are written. Shared, so many devices can log to
    // one history in the order things actually happened.
    private final ActivityLog log;

    // Both parts are handed in from outside: the caller decides which
    // device gets logged, and to which log.
    public LoggedDevice(Switchable inner, ActivityLog log) {
        // Remember the device to delegate to.
        this.inner = inner;
        // Remember the log to write to.
        this.log = log;
    }

    // Pure delegation: the wrapper has no name of its own.
    @Override
    public String getName() {
        // Ask the real device.
        return inner.getName();
    }

    // Delegate, then record what the device now reports.
    @Override
    public void turnOn() {
        // The real device switches on, in its own way.
        inner.turnOn();
        // Record its own status line, e.g. "Hall Thermostat: regulating to 21C".
        log.record(inner.status());
    }

    // Delegate, then record.
    @Override
    public void turnOff() {
        // The real device switches off.
        inner.turnOff();
        // Record its status line, e.g. "Living Room Light: OFF".
        log.record(inner.status());
    }

    // Pure delegation.
    @Override
    public boolean isOn() {
        // The real device knows its own state.
        return inner.isOn();
    }

    // Pure delegation: logging must not change how a device describes itself.
    @Override
    public String status() {
        // Exactly the real device's status line.
        return inner.status();
    }
}
