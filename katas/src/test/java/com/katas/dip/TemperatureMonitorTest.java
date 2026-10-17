// Same package as the policy under test.
package com.katas.dip;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for expected values.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless the condition is false.
import static org.junit.jupiter.api.Assertions.assertFalse;
// Fails unless the condition is true.
import static org.junit.jupiter.api.Assertions.assertTrue;

// Tests the POLICY alone: no email, no SMS, no console. Injection makes it easy.
class TemperatureMonitorTest {

    // Above the limit: exactly one alert, in the policy's own words.
    @Test
    void alertsAboveTheLimit() {
        // The fake channel, injected through the constructor.
        RecordingChannel channel = new RecordingChannel();
        TemperatureMonitor monitor = new TemperatureMonitor(channel, 30);
        // 35C is too hot.
        assertTrue(monitor.check(35));
        // One alert with the exact message.
        assertEquals(List.of("Server room at 35C (max 30C)"), channel.sent());
    }

    // At or below the limit: silence.
    @Test
    void staysSilentAtOrBelowTheLimit() {
        // A fresh fake channel.
        RecordingChannel channel = new RecordingChannel();
        TemperatureMonitor monitor = new TemperatureMonitor(channel, 30);
        // Exactly the limit is still fine...
        assertFalse(monitor.check(30));
        // ...and so is below it.
        assertFalse(monitor.check(20));
        // Nothing was sent.
        assertEquals(List.of(), channel.sent());
    }

    // The limit is the caller's choice, injected like the channel.
    @Test
    void theLimitIsInjectedToo() {
        // A stricter room: max 22C.
        RecordingChannel channel = new RecordingChannel();
        TemperatureMonitor monitor = new TemperatureMonitor(channel, 22);
        // 25C would be fine at 30, but not here.
        assertTrue(monitor.check(25));
        // The message names the injected limit.
        assertEquals(List.of("Server room at 25C (max 22C)"), channel.sent());
    }
}
