// Same package as the simulated sensor.
package com.smarthome.sensors;

// The contract the simulator keeps.
import com.smarthome.climate.TemperatureSensor;
// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves the simulator keeps the TemperatureSensor contract.
class SimulatedThermometerTest {

    // It reports its location and its reading through the contract.
    @Test
    void reportsItsLocationAndReading() {
        // Seen only through the policy's abstraction.
        TemperatureSensor sensor = new SimulatedThermometer("Hall", 21);
        // Location...
        assertEquals("Hall", sensor.location());
        // ...and reading.
        assertEquals(21, sensor.readCelsius());
    }

    // Changing the simulated temperature changes the next reading.
    @Test
    void newReadingsAreReported() {
        // The concrete class, because only the simulator can be "warmed up".
        SimulatedThermometer sensor = new SimulatedThermometer("Hall", 21);
        // The room heats up.
        sensor.setCelsius(27);
        // The next reading shows it.
        assertEquals(27, sensor.readCelsius());
    }
}
