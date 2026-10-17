// A test-only sensor, living under src/test so it never ships.
package com.smarthome.climate;

// The policy depends on TemperatureSensor, so a test can inject this tiny
// stand-in: no hardware, no simulator, just a fixed reading.
class FixedSensor implements TemperatureSensor {

    // The one reading this sensor will ever report.
    private final int celsius;

    // Build it with that reading.
    FixedSensor(int celsius) {
        // Remember the reading.
        this.celsius = celsius;
    }

    // Keeps the contract: a location for messages.
    @Override
    public String location() {
        // Always the same test room.
        return "Test Room";
    }

    // Keeps the contract: the fixed reading.
    @Override
    public int readCelsius() {
        // Hand it back.
        return celsius;
    }
}
