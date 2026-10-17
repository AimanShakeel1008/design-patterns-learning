// Same package as the contract test it extends.
package com.smarthome.devices;

// Runs the base Device promises AND the Switchable promises against Thermostat.
class ThermostatContractTest extends SwitchableContractTest {

    // Supply a Thermostat; the inherited tests do the rest.
    @Override
    protected Switchable createSwitchable(String name) {
        // Any valid target works; the contract does not care which.
        return new Thermostat(name, 21);
    }
}
