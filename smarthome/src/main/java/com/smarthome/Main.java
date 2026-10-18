// The package (namespace) for the Smart Home Hub's entry point. The folder
// path com/smarthome on disk must mirror this line exactly.
package com.smarthome;

// The shared activity history.
import com.smarthome.activity.ActivityLog;
// A concrete alert channel: allowed here, because this is the composition root.
import com.smarthome.alerts.ConsoleAlerts;
// The climate policy (Lesson 09).
import com.smarthome.climate.ClimateControl;
// How the house is operated.
import com.smarthome.home.Hub;
// How the house's state is displayed.
import com.smarthome.report.HouseReport;
// The modes the homeowner can run (Lesson 06).
import com.smarthome.routines.AllOn;
import com.smarthome.routines.KeepOnly;
// The security policy (Lesson 10).
import com.smarthome.security.LockCheck;
// A concrete sensor: allowed here, because this is the composition root.
import com.smarthome.sensors.SimulatedThermometer;

// List.of for the goodnight routine's keep-list.
import java.util.List;

// The application's entry point and its COMPOSITION ROOT (Lesson 09): the one
// place where concrete objects are created and plugged into each other by
// hand (manual dependency injection). Everything it builds receives its
// dependencies through constructors; nothing in the core creates its own.
public class Main {

    // The JVM's starting point: same required signature as any Java program.
    public static void main(String[] args) {
        // The startup banner, unchanged since Lesson 00.
        System.out.println("Smart Home Hub is starting up...");

        // --- Build the object graph ---
        // One shared history for the whole house.
        ActivityLog log = new ActivityLog();
        // The installer's choice of devices (HomeSetup is part of this root).
        HomeSetup setup = new HomeSetup();
        // Inject the three device groups into the hub.
        Hub hub = new Hub(setup.createSwitchables(log), setup.createLockables(), setup.createOtherDevices());
        // A concrete sensor detail, reading 26C in the hall.
        SimulatedThermometer hallSensor = new SimulatedThermometer("Hall", 26);
        // Inject the sensor and the thermostat (as a Switchable) into the policy.
        ClimateControl climate = new ClimateControl(hallSensor, hub.switchableNamed("Hall Thermostat"), 18, 24);
        // Inject the locks and a concrete channel into the security policy.
        LockCheck lockCheck = new LockCheck(hub.lockables(), new ConsoleAlerts());

        // --- Run one scenario ---
        // Morning: everything on.
        hub.run(new AllOn());
        // Goodnight: keep only the thermostat running.
        hub.run(new KeepOnly(List.of("Hall Thermostat")));
        // The hall is 26C, above the 24C maximum: the policy decides, and says so.
        System.out.println(climate.regulate());
        // Someone goes out and leaves the front door unlocked. Asked of the hub
        // by name (Law of Demeter), not by reaching into its list by position.
        hub.unlock("Front Door");
        // ...and the security check warns about it.
        lockCheck.run();

        // Display the result. print, not println: the report ends with its own newline.
        System.out.print(new HouseReport().render(hub.devices(), hub.switchables(), log.entries()));
    }
}
