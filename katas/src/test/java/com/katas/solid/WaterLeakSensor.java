// A test-only kind of watched thing, living under src/test so it never ships.
package com.katas.solid;

// A brand-new kind of problem, invented AFTER SecurityCheck was finished.
// It plugs in by keeping the Monitored role: no edit to the check (OCP).
class WaterLeakSensor implements Monitored {

    // The room it watches.
    private final String room;

    // Build one for a room; it always reports a leak, for the test.
    WaterLeakSensor(String room) {
        // Remember the room.
        this.room = room;
    }

    // Its name.
    @Override
    public String name() {
        // The room.
        return room;
    }

    // Always leaking, for this test.
    @Override
    public boolean needsAttention() {
        // Problem.
        return true;
    }

    // Water damage cannot wait until morning.
    @Override
    public boolean isUrgent() {
        // Urgent.
        return true;
    }

    // Its own description.
    @Override
    public String problem() {
        // e.g. "WATER LEAK in Basement".
        return "WATER LEAK in " + room;
    }
}
