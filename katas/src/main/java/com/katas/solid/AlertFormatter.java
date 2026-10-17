// STEP 1 (SRP): the wording of alerts, split out of the god class.
package com.katas.solid;

// Answers to whoever decides what the owner READS. A new wording or a
// translation changes this class only.
public class AlertFormatter {

    // Turn a problem into the alert text, marking night-time alerts.
    public String format(String problem, boolean nightTime) {
        // e.g. "[NIGHT] ALERT: SMOKE at Kitchen".
        return (nightTime ? "[NIGHT] " : "") + "ALERT: " + problem;
    }
}
