// A smoke alarm that promises only what an alarm can keep.
package com.katas.solid;

// Signs the small Monitored role and nothing else.
public class SmokeAlarm implements Monitored {

    // The room it protects.
    private final String name;

    // Whether smoke is currently detected.
    private final boolean smoke;

    // Build an alarm with its room and current reading.
    public SmokeAlarm(String name, boolean smoke) {
        // Store the name.
        this.name = name;
        // Store the reading.
        this.smoke = smoke;
    }

    // The name, for messages.
    @Override
    public String name() {
        // Hand back the name.
        return name;
    }

    // Smoke needs attention.
    @Override
    public boolean needsAttention() {
        // Problem exactly when smoke is detected.
        return smoke;
    }

    // Smoke is ALWAYS urgent, night or day.
    @Override
    public boolean isUrgent() {
        // Urgent.
        return true;
    }

    // How the alarm describes its problem.
    @Override
    public String problem() {
        // e.g. "SMOKE at Kitchen".
        return "SMOKE at " + name;
    }
}
