// Sensor DETAILS live in their own package.
package com.smarthome.sensors;

// The detail depends UP on the policy's abstraction: this import points from
// sensors to climate, never the other way round.
import com.smarthome.climate.TemperatureSensor;

// A software stand-in for a real thermometer: it reports whatever reading it
// was last given. Real hardware would implement the same interface (a later
// lesson plugs in a third-party sensor), and ClimateControl would not change.
public class SimulatedThermometer implements TemperatureSensor {

    // Where this thermometer hangs.
    private final String location;

    // The current simulated reading.
    private int celsius;

    // Build one with a location and a starting reading.
    public SimulatedThermometer(String location, int celsius) {
        // Remember the location.
        this.location = location;
        // Remember the reading.
        this.celsius = celsius;
    }

    // Simulate the room warming or cooling (only the simulator can do this).
    public void setCelsius(int celsius) {
        // Store the new reading.
        this.celsius = celsius;
    }

    // Keeps the TemperatureSensor contract: the location.
    @Override
    public String location() {
        // Hand back the field.
        return location;
    }

    // Keeps the TemperatureSensor contract: the latest reading.
    @Override
    public int readCelsius() {
        // Hand back the field.
        return celsius;
    }
}
