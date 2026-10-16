// The package (namespace) for the Smart Home Hub's entry point. The folder
// path com/smarthome on disk must mirror this line exactly.
package com.smarthome;

// The devices live in their own package, so we import the names we use.
// Device is the contract; Light and Thermostat are two implementations.
import com.smarthome.devices.Device;
import com.smarthome.devices.Light;
import com.smarthome.devices.Thermostat;

// The application's top-level class. File name Main.java matches class Main.
public class Main {

    // The JVM's starting point: same required signature as any Java program.
    public static void main(String[] args) {
        // The startup banner, unchanged since Lesson 00.
        System.out.println("Smart Home Hub is starting up...");

        // Build the devices. This is the ONLY place the concrete class names
        // Light and Thermostat appear in the hub's running code.
        Device[] devices = {
                // A light needs only a name.
                new Light("Living Room Light"),
                // A thermostat also needs its starting target temperature.
                new Thermostat("Hall Thermostat", 21)
        };

        // From here on the hub knows only "these are Devices". The loop is
        // written once and works for every device kind, now and in the future.
        for (Device device : devices) {
            // The same call: each device runs its own turnOn code.
            device.turnOn();
            // Each device describes itself in its own words; indent for reading.
            System.out.println("  " + device.status());
        }
    }
}
