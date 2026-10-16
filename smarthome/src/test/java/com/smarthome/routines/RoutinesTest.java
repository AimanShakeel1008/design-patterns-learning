// Same package as the routines.
package com.smarthome.routines;

// A log and the logging wrapper, to count what each routine really switches.
import com.smarthome.activity.ActivityLog;
import com.smarthome.activity.LoggedDevice;
// The device contract and two plain devices.
import com.smarthome.devices.Device;
import com.smarthome.devices.Light;
import com.smarthome.devices.Thermostat;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for inputs and expected values.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless the condition is false.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Proves each routine does its one job, through the Device contract only.
class RoutinesTest {

    // "Away" switches everything off.
    @Test
    void allOffSwitchesEverythingOff() {
        // Two devices, both on.
        Device light = new Light("Hall Light");
        Device thermostat = new Thermostat("Hall", 21);
        List<Device> devices = List.of(light, thermostat);
        new AllOn().applyTo(devices);
        // Leave the house.
        new AllOff().applyTo(devices);
        // Both off.
        assertFalse(light.isOn());
        assertFalse(thermostat.isOn());
    }

    // "Goodnight": only the named device stays on.
    @Test
    void keepOnlyLeavesTheNamedDevicesOnAndTheRestOff() {
        // Two devices, both on.
        Device light = new Light("Hall Light");
        Device thermostat = new Thermostat("Hall", 21);
        List<Device> devices = List.of(light, thermostat);
        new AllOn().applyTo(devices);
        // Keep only the thermostat.
        new KeepOnly(List.of("Hall")).applyTo(devices);
        // The thermostat stays on; the light went off.
        assertTrue(thermostat.isOn());
        assertFalse(light.isOn());
    }

    // Routines switch only what must change, so the history has no duplicates.
    @Test
    void routinesNeverSwitchADeviceThatIsAlreadyInTheRightState() {
        // A logged light, so every real switch leaves a log line.
        ActivityLog log = new ActivityLog();
        List<Device> devices = List.of(new LoggedDevice(new Light("Desk Lamp"), log));
        // On twice, then "keep it on": only the FIRST call should switch.
        new AllOn().applyTo(devices);
        new AllOn().applyTo(devices);
        new KeepOnly(List.of("Desk Lamp")).applyTo(devices);
        // Exactly one entry.
        assertEquals(List.of("Desk Lamp: ON"), log.entries());
    }
}
