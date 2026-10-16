// Code about the house as a whole lives in the "home" package.
package com.smarthome.home;

// The logging wrapper and the log it writes to.
import com.smarthome.activity.ActivityLog;
import com.smarthome.activity.LoggedDevice;
// The contract and the concrete device kinds this house contains.
import com.smarthome.devices.Device;
import com.smarthome.devices.Light;
import com.smarthome.devices.Thermostat;

// The JDK's list interface.
import java.util.List;

// ONE responsibility: "which devices does this house have, and how are they
// wired?" It answers to whoever installs the house. Adding a device changes
// this class and nothing else.
public class HomeSetup {

    // Build every device in the house, each wrapped so its switching is logged.
    public List<Device> createDevices(ActivityLog log) {
        // An unmodifiable list: the setup is fixed once created.
        return List.of(
                // A real Light inside a LoggedDevice.
                new LoggedDevice(new Light("Living Room Light"), log),
                // A real Thermostat inside a LoggedDevice.
                new LoggedDevice(new Thermostat("Hall Thermostat", 21), log)
        );
    }
}
