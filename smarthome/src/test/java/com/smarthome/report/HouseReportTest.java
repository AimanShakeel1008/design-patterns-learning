// Same package as HouseReport.
package com.smarthome.report;

// Devices of every kind to report on.
import com.smarthome.devices.Device;
import com.smarthome.devices.DoorLock;
import com.smarthome.devices.Light;
import com.smarthome.devices.SmokeAlarm;
import com.smarthome.devices.Switchable;
import com.smarthome.devices.Thermostat;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for inputs.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves the report's one job: exact formatting, for devices of every kind.
class HouseReportTest {

    // A typical house: two switchable devices (one on), an alarm, a lock.
    @Test
    void rendersCountsStatusesAndNumberedActivity() {
        // A light that is off and a thermostat that is on.
        Switchable light = new Light("Living Room Light");
        Switchable thermostat = new Thermostat("Hall Thermostat", 21);
        thermostat.turnOn();
        // Two devices that cannot switch.
        Device alarm = new SmokeAlarm("Hallway Smoke Alarm");
        Device door = new DoorLock("Front Door");
        // Three history lines.
        List<String> activity = List.of("Living Room Light: ON", "Hall Thermostat: regulating to 21C",
                "Living Room Light: OFF");
        // Render: every device for the lines, the switchable ones for counting.
        String text = new HouseReport().render(List.of(light, thermostat, alarm, door),
                List.of(light, thermostat), activity);
        // Every line, exactly.
        assertEquals("=== House report ===\n"
                + "Devices: 4 (2 switchable, 1 on)\n"
                + "  Living Room Light: OFF\n"
                + "  Hall Thermostat: regulating to 21C\n"
                + "  Hallway Smoke Alarm: ARMED\n"
                + "  Front Door: LOCKED\n"
                + "Activity (3 entries):\n"
                + "  1. Living Room Light: ON\n"
                + "  2. Hall Thermostat: regulating to 21C\n"
                + "  3. Living Room Light: OFF\n", text);
    }

    // An empty house still produces a well-formed report.
    @Test
    void emptyHouseRendersZeros() {
        // No devices, no history.
        String text = new HouseReport().render(List.of(), List.of(), List.of());
        // Heading and zero counts.
        assertEquals("=== House report ===\n"
                + "Devices: 0 (0 switchable, 0 on)\n"
                + "Activity (0 entries):\n", text);
    }
}
