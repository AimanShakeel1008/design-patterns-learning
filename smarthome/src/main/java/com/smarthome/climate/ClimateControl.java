// The climate POLICY: high-level rules about comfort.
package com.smarthome.climate;

// The only device capability the policy needs: switching the heating.
import com.smarthome.devices.Switchable;

// Keeps a room between a minimum and a maximum temperature by switching the
// heating on or off. It depends ONLY on abstractions (TemperatureSensor,
// Switchable), both injected through the constructor; it never names or
// creates a sensor class or a thermostat class.
public class ClimateControl {

    // Where the readings come from: any sensor keeping the contract.
    private final TemperatureSensor sensor;

    // What heats the room: any switchable device (in the hub, the thermostat).
    private final Switchable heating;

    // The lowest comfortable temperature.
    private final int minCelsius;

    // The highest comfortable temperature.
    private final int maxCelsius;

    // CONSTRUCTOR INJECTION: every dependency and setting arrives from outside.
    public ClimateControl(TemperatureSensor sensor, Switchable heating, int minCelsius, int maxCelsius) {
        // A range upside down can never be satisfied: refuse it.
        if (minCelsius > maxCelsius) {
            // Loud refusal naming both values.
            throw new IllegalArgumentException("Range is upside down: " + minCelsius + "-" + maxCelsius + "C");
        }
        // Remember the sensor.
        this.sensor = sensor;
        // Remember the heating.
        this.heating = heating;
        // Remember the lower bound.
        this.minCelsius = minCelsius;
        // Remember the upper bound.
        this.maxCelsius = maxCelsius;
    }

    // Read the sensor once, act if needed, and describe what happened.
    public String regulate() {
        // One reading through the abstraction.
        int celsius = sensor.readCelsius();
        // What we decided to do, in words.
        String action;
        // Too cold: heating must be on.
        if (celsius < minCelsius) {
            // Switch only if it is not already on (no duplicate log entries).
            if (!heating.isOn()) {
                // On, through the Switchable contract.
                heating.turnOn();
            }
            // Describe the decision.
            action = "heating ON";
        } else if (celsius > maxCelsius) {
            // Too hot: heating must be off. Switch only if needed.
            if (heating.isOn()) {
                // Off, through the Switchable contract.
                heating.turnOff();
            }
            // Describe the decision.
            action = "heating OFF";
        } else {
            // Comfortable: leave everything as it is.
            action = "no change";
        }
        // e.g. "Climate: 26C in Hall (allowed 18-24C) -> heating OFF".
        return "Climate: " + celsius + "C in " + sensor.location()
                + " (allowed " + minCelsius + "-" + maxCelsius + "C) -> " + action;
    }
}
