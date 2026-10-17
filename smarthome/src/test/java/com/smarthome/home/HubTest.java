// Same package as Hub.
package com.smarthome.home;

// Plain devices: the hub does not care whether they are logged.
import com.smarthome.devices.Device;
import com.smarthome.devices.DoorLock;
import com.smarthome.devices.Light;
import com.smarthome.devices.SmokeAlarm;
import com.smarthome.devices.Switchable;
import com.smarthome.devices.Thermostat;
// Routines used to operate the house in these tests.
import com.smarthome.routines.AllOff;
import com.smarthome.routines.AllOn;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the device lists.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless the condition is false.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Proves the hub's one job: operating the devices it is given, through the
// right capability for each.
class HubTest {

    // run hands the switchable devices to the routine (here: switch everything on).
    @Test
    void runAppliesTheRoutineToEverySwitchableDevice() {
        // Two plain switchable devices.
        Light light = new Light("Desk Lamp");
        Thermostat thermostat = new Thermostat("Office", 20);
        // A hub operating them, with no other devices.
        Hub hub = new Hub(List.of(light, thermostat), List.of());
        // Run the "everything on" routine.
        hub.run(new AllOn());
        // Both are on.
        assertTrue(light.isOn());
        assertTrue(thermostat.isOn());
    }

    // turnOff finds the device by name and switches only that one off.
    @Test
    void turnOffSwitchesOnlyTheNamedDevice() {
        // Two lights, both started.
        Switchable desk = new Light("Desk Lamp");
        Switchable porch = new Light("Porch Light");
        Hub hub = new Hub(List.of(desk, porch), List.of());
        hub.run(new AllOn());
        // Switch one off by name.
        hub.turnOff("Porch Light");
        // Only the named one changed.
        assertTrue(desk.isOn());
        assertFalse(porch.isOn());
    }

    // An unknown name is refused loudly.
    @Test
    void unknownNameIsRefused() {
        // A one-device hub.
        Hub hub = new Hub(List.of(new Light("Desk Lamp")), List.of());
        // Asking for a device that does not exist must throw.
        assertThrows(IllegalArgumentException.class, () -> hub.turnOff("Garage Light"));
    }

    // Every device is listed, but "Away" only ever touches the switchable ones.
    @Test
    void hubListsEveryDeviceButRoutinesSeeOnlySwitchables() {
        // One switchable device, plus an alarm and a lock that cannot switch.
        Light light = new Light("Hall Light");
        Device alarm = new SmokeAlarm("Hallway Smoke Alarm");
        DoorLock door = new DoorLock("Front Door");
        Hub hub = new Hub(List.of(light), List.of(alarm, door));
        // All three are in the house...
        assertEquals(3, hub.devices().size());
        // ...but only one is switchable.
        assertEquals(1, hub.switchables().size());
        // "Away" runs safely: the alarm and the lock are never handed to it.
        hub.run(new AllOn());
        hub.run(new AllOff());
        // The light went off; the alarm is still armed; the door is still locked.
        assertFalse(light.isOn());
        assertEquals("Hallway Smoke Alarm: ARMED", alarm.status());
        assertTrue(door.isLocked());
    }
}
