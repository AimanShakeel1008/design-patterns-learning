// Same package as the contract test it extends.
package com.smarthome.devices;

// Runs all five Device promises against Light.
class LightContractTest extends DeviceContractTest {

    // Supply a Light; the inherited tests do the rest.
    @Override
    protected Device createDevice(String name) {
        // A plain light with the requested name.
        return new Light(name);
    }
}
