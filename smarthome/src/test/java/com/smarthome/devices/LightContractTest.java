// Same package as the contract test it extends.
package com.smarthome.devices;

// Runs the base Device promises AND the Switchable promises against Light.
class LightContractTest extends SwitchableContractTest {

    // Supply a Light; the inherited tests do the rest.
    @Override
    protected Switchable createSwitchable(String name) {
        // A plain light with the requested name.
        return new Light(name);
    }
}
