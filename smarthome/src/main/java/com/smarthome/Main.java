// The package (namespace) for the Smart Home Hub's entry point. The folder
// path com/smarthome on disk must mirror this line exactly.
package com.smarthome;

// The shared activity history.
import com.smarthome.activity.ActivityLog;
// Which devices the house has (HomeSetup) and how it is operated (Hub).
import com.smarthome.home.HomeSetup;
import com.smarthome.home.Hub;
// How the house's state is displayed.
import com.smarthome.report.HouseReport;

// The application's entry point. Since Lesson 05 it has ONE job: wire the
// pieces together and run one scenario. Each piece's real work lives in a
// class that answers to one kind of change.
public class Main {

    // The JVM's starting point: same required signature as any Java program.
    public static void main(String[] args) {
        // The startup banner, unchanged since Lesson 00.
        System.out.println("Smart Home Hub is starting up...");

        // One shared history for the whole house.
        ActivityLog log = new ActivityLog();
        // Ask the setup for this house's devices, wired to the log.
        Hub hub = new Hub(new HomeSetup().createDevices(log));

        // Operate the house: everything on...
        hub.startUp();
        // ...then the living room light off.
        hub.turnOff("Living Room Light");

        // Display the result. print, not println: the report ends with its own newline.
        System.out.print(new HouseReport().render(hub.devices(), log.entries()));
    }
}
