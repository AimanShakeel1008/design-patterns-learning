// Lives with LoggedDevice; reaches the contract test by import.
package com.smarthome.activity;

// The switching contract, its contract test, and a real device to wrap.
import com.smarthome.devices.Light;
import com.smarthome.devices.Switchable;
import com.smarthome.devices.SwitchableContractTest;

// Runs the base Device promises AND the Switchable promises against the
// logging WRAPPER. A wrapper is only safe to hand out in place of a device
// if it keeps the same contract.
class LoggedDeviceContractTest extends SwitchableContractTest {

    // Supply a logged light; the inherited tests do the rest.
    @Override
    protected Switchable createSwitchable(String name) {
        // A real light inside the wrapper, with its own throwaway log.
        return new LoggedDevice(new Light(name), new ActivityLog());
    }
}
