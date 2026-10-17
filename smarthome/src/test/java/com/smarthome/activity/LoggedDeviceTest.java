// Same package as LoggedDevice.
package com.smarthome.activity;

// The switching contract and two real devices to wrap.
import com.smarthome.devices.Light;
import com.smarthome.devices.Switchable;
import com.smarthome.devices.Thermostat;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the expected entries.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Proves the wrapper delegates faithfully and logs every switch.
class LoggedDeviceTest {

    // Switching on and off is delegated AND recorded.
    @Test
    void switchingIsDelegatedAndRecorded() {
        // A log and a wrapped light, held through the contract.
        ActivityLog log = new ActivityLog();
        Switchable light = new LoggedDevice(new Light("Porch Light"), log);
        // On, then off.
        light.turnOn();
        light.turnOff();
        // Both events recorded, in order, in the light's own words.
        assertEquals(List.of("Porch Light: ON", "Porch Light: OFF"), log.entries());
    }

    // The wrapper changes nothing about what the device says or reports.
    @Test
    void wrapperIsInvisibleToClients() {
        // A wrapped thermostat.
        Switchable thermostat = new LoggedDevice(new Thermostat("Hall", 21), new ActivityLog());
        // Switch it on through the wrapper.
        thermostat.turnOn();
        // Name, state and status all come straight from the real thermostat.
        assertEquals("Hall", thermostat.getName());
        assertTrue(thermostat.isOn());
        assertEquals("Hall: regulating to 21C", thermostat.status());
    }

    // One shared log records events from different devices in true order.
    @Test
    void sharedLogKeepsTheOrderAcrossDevices() {
        // One log, two different devices wrapped around it.
        ActivityLog log = new ActivityLog();
        Switchable light = new LoggedDevice(new Light("Desk Lamp"), log);
        Switchable thermostat = new LoggedDevice(new Thermostat("Office", 20), log);
        // Interleaved events.
        light.turnOn();
        thermostat.turnOn();
        light.turnOff();
        // Recorded exactly as they happened.
        assertEquals(List.of("Desk Lamp: ON", "Office: regulating to 20C", "Desk Lamp: OFF"), log.entries());
    }
}
