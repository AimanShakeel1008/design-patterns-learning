// Since Lesson 09 this lives beside Main, in the COMPOSITION ROOT: the one
// place allowed to name concrete classes. The core packages (home, routines,
// report, climate) never import a concrete device or sensor.
package com.smarthome;

// The logging wrapper and the log it writes to.
import com.smarthome.activity.ActivityLog;
import com.smarthome.activity.LoggedDevice;
// The contracts and the concrete device kinds this house contains.
import com.smarthome.devices.Device;
import com.smarthome.devices.DoorLock;
import com.smarthome.devices.Light;
import com.smarthome.devices.Lockable;
import com.smarthome.devices.SmokeAlarm;
import com.smarthome.devices.Switchable;
import com.smarthome.devices.Thermostat;

// The JDK's list interface.
import java.util.List;

// ONE responsibility: "which devices does this house have, and how are they
// wired?" It answers to whoever installs the house. It hands over devices
// grouped by capability (Lesson 08), typed by their contracts, so everything
// it builds can be injected into the core without the core knowing the classes.
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

    // The devices that can be locked (their own group since Lesson 10, so the
    // security check receives them typed as Lockable).
    public List<Lockable> createLockables() {
        // An unmodifiable list.
        return List.of(
                // Starts locked.
                new DoorLock("Front Door")
        );
    }

    // The devices that promise only the base Device contract.
    public List<Device> createOtherDevices() {
        // An unmodifiable list.
        return List.of(
                // Always armed; nobody can switch it off, not even "Away".
                new SmokeAlarm("Hallway Smoke Alarm")
        );
    }
}
