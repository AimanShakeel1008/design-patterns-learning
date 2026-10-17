// The refactored module. STEP 2 (OCP) + STEP 4 (ISP): one small role
// interface instead of a fat device contract and a ladder of text tags.
package com.katas.solid;

// What the security check needs from anything it watches, and nothing more.
// Each kind of watched thing answers for itself, so a new kind (a window
// sensor, a water-leak detector) is a new class, not a new branch in a ladder.
public interface Monitored {

    // A name for messages, e.g. "Front Door".
    String name();

    // Is something wrong right now?
    boolean needsAttention();

    // Must the owner hear about it even during quiet hours?
    boolean isUrgent();

    // One line describing the problem, e.g. "Front Door is UNLOCKED".
    String problem();
}
