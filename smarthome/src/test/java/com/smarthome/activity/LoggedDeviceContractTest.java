// Lives with LoggedDevice; reaches the contract test by import.
package com.smarthome.activity;

// The device contract, the contract test, and a real device to wrap.
import com.smarthome.devices.Device;
import com.smarthome.devices.DeviceContractTest;
import com.smarthome.devices.Light;

// Runs all five Device promises against the logging WRAPPER. A wrapper is
// only safe to hand out in place of a device if it keeps the same contract.
class LoggedDeviceContractTest extends DeviceContractTest {

    // Supply a logged light; the inherited tests do the rest.
    @Override
    protected Device createDevice(String name) {
        // A real light inside the wrapper, with its own throwaway log.
        return new LoggedDevice(new Light(name), new ActivityLog());
    }
}
