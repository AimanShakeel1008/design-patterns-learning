// Same package as HouseReport.
package com.smarthome.report;

// Plain devices to report on.
import com.smarthome.devices.Device;
import com.smarthome.devices.Light;
import com.smarthome.devices.Thermostat;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for inputs.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves the report's one job: exact formatting. Possible only because the
// report RETURNS text instead of printing it (it was buried in Main before).
class HouseReportTest {

    // A typical house: one device off, one on, three activity entries.
    @Test
    void rendersCountsStatusesAndNumberedActivity() {
        // A light that is off and a thermostat that is on.
        Device light = new Light("Living Room Light");
        Device thermostat = new Thermostat("Hall Thermostat", 21);
        thermostat.turnOn();
        // Three history lines.
        List<String> activity = List.of("Living Room Light: ON", "Hall Thermostat: regulating to 21C",
                "Living Room Light: OFF");
        // Render.
        String text = new HouseReport().render(List.of(light, thermostat), activity);
        // Every line, exactly.
        assertEquals("=== House report ===\n"
                + "Devices: 2 (1 on)\n"
                + "  Living Room Light: OFF\n"
                + "  Hall Thermostat: regulating to 21C\n"
                + "Activity (3 entries):\n"
                + "  1. Living Room Light: ON\n"
                + "  2. Hall Thermostat: regulating to 21C\n"
                + "  3. Living Room Light: OFF\n", text);
    }

    // An empty house still produces a well-formed report.
    @Test
    void emptyHouseRendersZeros() {
        // No devices, no history.
        String text = new HouseReport().render(List.of(), List.of());
        // Heading and two zero counts.
        assertEquals("=== House report ===\n"
                + "Devices: 0 (0 on)\n"
                + "Activity (0 entries):\n", text);
    }
}
