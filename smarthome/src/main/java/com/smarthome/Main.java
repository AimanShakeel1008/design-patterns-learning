// The package (namespace) for the Smart Home Hub's entry point. The folder
// path com/smarthome on disk must mirror this line exactly.
package com.smarthome;

// Import the device types we just created from their package. The hub's entry
// point lives in com.smarthome; the devices live in com.smarthome.devices, so
// we must name them to use them here.
import com.smarthome.devices.Device;
import com.smarthome.devices.Light;
import com.smarthome.devices.Thermostat;

// The application's top-level class. File name Main.java matches class Main.
public class Main {

    // The JVM's starting point: same required signature as any Java program.
    public static void main(String[] args) {
        // A friendly startup banner.
        System.out.println("Smart Home Hub is starting up...");

        // Build two very different devices. Notice each is created with its own
        // constructor and its own extra data (the thermostat also takes a target).
        Light livingRoomLight = new Light("Living Room Light");
        Thermostat hallThermostat = new Thermostat("Hall Thermostat", 21);

        // THE LOAD-BEARING MOVE: hold them both in an array typed as Device — the
        // CONTRACT — not as Light or Thermostat. From here on the hub only knows
        // "these are Devices," nothing about their concrete classes.
        Device[] devices = { livingRoomLight, hallThermostat };

        // One single loop controls every device the same way. It calls turnOn()
        // and status() through the Device contract; each object supplies its own
        // real behaviour. Adding a new device type later needs NO change here.
        for (Device device : devices) {
            // Turn the device on — a light lights, a thermostat starts regulating.
            device.turnOn();
            // Print whatever that device chooses to say about itself.
            System.out.println("  " + device.status());
        }
    }
}
