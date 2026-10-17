// The climate POLICY package. It owns this abstraction, so it lives here,
// not in the sensors package that implements it.
package com.smarthome.climate;

// What the climate policy needs from "a thermometer", in the policy's own
// terms. Real sensor hardware (and the simulated one in com.smarthome.sensors)
// implements THIS interface, so the low-level detail depends on the
// high-level policy, not the other way round: the Dependency Inversion
// Principle.
public interface TemperatureSensor {

    // Where the sensor is, e.g. "Hall"; used in messages.
    String location();

    // The current temperature, in whole degrees Celsius.
    int readCelsius();
}
