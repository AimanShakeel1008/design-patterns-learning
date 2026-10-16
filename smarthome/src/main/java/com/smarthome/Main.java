// The package (namespace) for the Smart Home Hub's entry point. The folder
// path com/smarthome on disk must mirror this line exactly.
package com.smarthome;

// The activity-logging feature (Lesson 03).
import com.smarthome.activity.ActivityLog;
import com.smarthome.activity.LoggedDevice;
// Device is the contract; Light and Thermostat are two implementations.
import com.smarthome.devices.Device;
import com.smarthome.devices.Light;
import com.smarthome.devices.Thermostat;

// List is the type of the log's entries.
import java.util.List;

// The application's top-level class. File name Main.java matches class Main.
public class Main {

    // The JVM's starting point: same required signature as any Java program.
    public static void main(String[] args) {
        // The startup banner, unchanged since Lesson 00.
        System.out.println("Smart Home Hub is starting up...");

        // One shared history for the whole house.
        ActivityLog log = new ActivityLog();

        // Build the devices, each wrapped so its switching is logged. The
        // wrapping is chosen HERE, per device, while the program runs; no
        // LoggingLight or LoggingThermostat class exists or is needed.
        Device[] devices = {
                // A real Light inside a LoggedDevice.
                new LoggedDevice(new Light("Living Room Light"), log),
                // A real Thermostat inside a LoggedDevice.
                new LoggedDevice(new Thermostat("Hall Thermostat", 21), log)
        };

        // The loop is unchanged: it still knows only "these are Devices".
        for (Device device : devices) {
            // Goes to LoggedDevice.turnOn, which delegates and records.
            device.turnOn();
            // Each device describes itself in its own words; indent for reading.
            System.out.println("  " + device.status());
        }

        // Someone switches the living room light off later.
        devices[0].turnOff();

        // Read the history once, as an unmodifiable snapshot.
        List<String> entries = log.entries();
        // A heading that says how many entries there are.
        System.out.println("Activity log (" + entries.size() + " entries):");
        // Print each entry numbered from 1.
        for (int i = 0; i < entries.size(); i++) {
            // i starts at 0, so add 1 for human numbering.
            System.out.println("  " + (i + 1) + ". " + entries.get(i));
        }
    }
}
