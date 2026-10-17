// Presentation code lives in its own "report" package.
package com.smarthome.report;

// The base contract (for names and statuses) and one capability (for on/off).
import com.smarthome.devices.Device;
import com.smarthome.devices.Switchable;

// The JDK's list interface.
import java.util.List;

// ONE responsibility: how the house's state LOOKS as text. It changes nothing;
// it only reads and formats. Since Lesson 08 it asks for exactly what it
// uses: Device (name + status) for every line, Switchable only for counting.
public class HouseReport {

    // Turn the devices, the switchable ones, and the history into one block of text.
    public String render(List<Device> devices, List<Switchable> switchables, List<String> activity) {
        // StringBuilder: a growable piece of text; append() adds to its end.
        StringBuilder text = new StringBuilder();
        // The heading.
        text.append("=== House report ===\n");
        // Count how many switchable devices are currently on.
        int onCount = 0;
        // Only switchable devices have an on/off state to count...
        for (Switchable device : switchables) {
            // ...so count the ones that are on.
            if (device.isOn()) {
                // One more device on.
                onCount++;
            }
        }
        // Summary line, e.g. "Devices: 4 (2 switchable, 1 on)".
        text.append("Devices: ").append(devices.size())
                .append(" (").append(switchables.size()).append(" switchable, ")
                .append(onCount).append(" on)\n");
        // One indented status line per device, of any kind.
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
