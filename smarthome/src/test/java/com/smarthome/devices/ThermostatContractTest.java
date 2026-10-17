// Same package as the contract test it extends.
package com.smarthome.devices;

// Runs all five Device promises against Thermostat.
class ThermostatContractTest extends DeviceContractTest {

    // Supply a Thermostat; the inherited tests do the rest.
    @Override
    protected Device createDevice(String name) {
        // Any valid target works; the contract does not care which.
        return new Thermostat(name, 21);
    }
}
