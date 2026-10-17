// Same package as the refactored module.
package com.katas.solid;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for inputs and expected values.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves the refactored module's behaviour, one principle's payoff per test.
class SecurityCheckTest {

    // The house used by most tests: an unlocked door and smoke in the kitchen.
    private final List<Monitored> troubledHouse =
            List.of(new DoorLock("Front Door", false), new SmokeAlarm("Kitchen", true));

    // By day, every problem is reported, in order, through the injected channel.
    @Test
    void reportsEveryProblemByDay() {
        // The test channel (DIP payoff: no email, no console).
        RecordingAlerts alerts = new RecordingAlerts();
        // Quiet hours ON, but it is daytime.
        new SecurityCheck(troubledHouse, alerts, new AlertFormatter(), true).run(false);
        // Both alerts, worded by the formatter.
        assertEquals(List.of("ALERT: Front Door is UNLOCKED", "ALERT: SMOKE at Kitchen"), alerts.sent());
    }

    // At night with quiet hours, the door waits but the smoke never does.
    @Test
    void quietNightHoldsBackOnlyNonUrgentProblems() {
        // The test channel.
        RecordingAlerts alerts = new RecordingAlerts();
        // Night, quiet hours ON (LSP payoff: a setting, not a refusing subclass).
        new SecurityCheck(troubledHouse, alerts, new AlertFormatter(), true).run(true);
        // Only the urgent smoke alert, marked as a night alert.
        assertEquals(List.of("[NIGHT] ALERT: SMOKE at Kitchen"), alerts.sent());
    }

    // A calm house sends nothing.
    @Test
    void calmHouseSendsNothing() {
        // A locked door and no smoke.
        List<Monitored> calm = List.of(new DoorLock("Front Door", true), new SmokeAlarm("Kitchen", false));
        // The test channel.
        RecordingAlerts alerts = new RecordingAlerts();
        // Check, by day.
        List<String> sent = new SecurityCheck(calm, alerts, new AlertFormatter(), true).run(false);
        // Nothing sent, nothing returned.
        assertEquals(List.of(), alerts.sent());
        assertEquals(List.of(), sent);
    }

    // A brand-new kind of watched thing needs no change to SecurityCheck (OCP payoff).
    @Test
    void aNewKindOfProblemPlugsInWithoutEditingTheCheck() {
        // A water-leak sensor invented in the test folder, after the check was written.
        Monitored leak = new WaterLeakSensor("Basement");
        // The test channel.
        RecordingAlerts alerts = new RecordingAlerts();
        // At night, quiet hours ON: urgent, so it is sent.
        new SecurityCheck(List.of(leak), alerts, new AlertFormatter(), true).run(true);
        // Reported, through the unchanged check.
        assertEquals(List.of("[NIGHT] ALERT: WATER LEAK in Basement"), alerts.sent());
    }
}
