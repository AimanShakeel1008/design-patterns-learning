// Code about the house as a whole lives in the "home" package.
package com.smarthome.home;

// The logging wrapper and the log it writes to.
import com.smarthome.activity.ActivityLog;
import com.smarthome.activity.LoggedDevice;
// The contracts and the concrete device kinds this house contains.
import com.smarthome.devices.Device;
import com.smarthome.devices.DoorLock;
import com.smarthome.devices.Light;
import com.smarthome.devices.SmokeAlarm;
import com.smarthome.devices.Switchable;
import com.smarthome.devices.Thermostat;

// The JDK's list interface.
import java.util.List;

// ONE responsibility: "which devices does this house have, and how are they
// wired?" It answers to whoever installs the house. Since Lesson 08 it hands
// over devices grouped by capability, so the hub never has to ask an object
// what it can do.
public class HomeSetup {

    // The devices that can be switched, each wrapped so its switching is logged.
    public List<Switchable> createSwitchables(ActivityLog log) {
        // An unmodifiable list: the setup is fixed once created.
        return List.of(
                // A real Light inside a LoggedDevice.
                new LoggedDevice(new Light("Living Room Light"), log),
                // A real Thermostat inside a LoggedDevice.
                new LoggedDevice(new Thermostat("Hall Thermostat", 21), log)
        );
    }

    // The devices that cannot be switched: they promise only the base contract
    // (and, for the lock, the Lockable capability).
    public List<Device> createOtherDevices() {
        // An unmodifiable list.
        return List.of(
                // Always armed; nobody can switch it off, not even "Away".
                new SmokeAlarm("Hallway Smoke Alarm"),
                // Starts locked.
                new DoorLock("Front Door")
        );
    }
}
