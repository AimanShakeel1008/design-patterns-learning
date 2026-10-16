// Presentation code lives in its own "report" package.
package com.smarthome.report;

// The contract: the report reads devices, whatever their kind.
import com.smarthome.devices.Device;

// The JDK's list interface.
import java.util.List;

// ONE responsibility: how the house's state LOOKS as text. It answers to
// whoever designs the display. It changes nothing; it only reads and formats.
public class HouseReport {

    // Turn the devices and the activity history into one block of text.
    public String render(List<Device> devices, List<String> activity) {
        // StringBuilder: a growable piece of text; append() adds to its end.
        StringBuilder text = new StringBuilder();
        // The heading.
        text.append("=== House report ===\n");
        // Count how many devices are currently on.
        int onCount = 0;
        // Look at each device...
        for (Device device : devices) {
            // ...and count it if it is on.
            if (device.isOn()) {
                // One more device on.
                onCount++;
            }
        }
        // Summary line, e.g. "Devices: 2 (1 on)".
        text.append("Devices: ").append(devices.size()).append(" (").append(onCount).append(" on)\n");
        // One indented status line per device.
        for (Device device : devices) {
            // Each device describes itself in its own words.
            text.append("  ").append(device.status()).append("\n");
        }
        // The activity heading, with the number of entries.
        text.append("Activity (").append(activity.size()).append(" entries):\n");
        // Each entry numbered from 1.
        for (int i = 0; i < activity.size(); i++) {
            // i starts at 0, so add 1 for human numbering.
            text.append("  ").append(i + 1).append(". ").append(activity.get(i)).append("\n");
        }
        // The finished text.
        return text.toString();
    }
}
