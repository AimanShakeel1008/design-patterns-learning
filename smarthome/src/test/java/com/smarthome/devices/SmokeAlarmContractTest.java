// Same package as the contract test it extends.
package com.smarthome.devices;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// A smoke alarm signs ONLY the base Device contract, so it runs only the base
// tests (plus one of its own). There are no switching tests to fail, because
// it never promised to switch: the Lesson 07 problem is gone.
class SmokeAlarmContractTest extends DeviceContractTest {

    // Supply a SmokeAlarm; the inherited tests do the rest.
    @Override
    protected Device createDevice(String name) {
        // An alarm with the requested name.
        return new SmokeAlarm(name);
    }

    // Its own behaviour: always armed.
    @Test
    void smokeAlarmIsAlwaysArmed() {
        // A named alarm reports itself armed.
        assertEquals("Hallway Smoke Alarm: ARMED", new SmokeAlarm("Hallway Smoke Alarm").status());
    }
}
