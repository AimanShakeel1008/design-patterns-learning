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
// The modes the homeowner can run (Lesson 06).
import com.smarthome.routines.AllOn;
import com.smarthome.routines.KeepOnly;

// List.of for the goodnight routine's keep-list.
import java.util.List;

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
        // Ask the setup for this house's devices, grouped by capability:
        // switchable ones (wired to the log) and all the others.
        HomeSetup setup = new HomeSetup();
        // The hub keeps both groups; routines will only ever see the first.
        Hub hub = new Hub(setup.createSwitchables(log), setup.createOtherDevices());

        // Operate the house by running routines. Which modes exist is chosen
        // HERE, by creating routine objects; the hub never names them.
        // Morning: everything on.
        hub.run(new AllOn());
        // Goodnight: keep only the thermostat running.
        hub.run(new KeepOnly(List.of("Hall Thermostat")));

        // Display the result. print, not println: the report ends with its own newline.
        System.out.print(new HouseReport().render(hub.devices(), hub.switchables(), log.entries()));
    }
}
