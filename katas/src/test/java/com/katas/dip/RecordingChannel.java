// A test-only channel, living under src/test so it never ships.
package com.katas.dip;

// The JDK's growable list.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// Possible ONLY because the policy depends on AlertChannel rather than on a
// concrete sender: the test injects this instead of email or SMS.
class RecordingChannel implements AlertChannel {

    // Every alert received, in order.
    private final List<String> sent = new ArrayList<>();

    // "Deliver" by remembering.
    @Override
    public void send(String message) {
        // Record it for the test.
        sent.add(message);
    }

    // What was sent, as a read-only snapshot.
    List<String> sent() {
        // An unmodifiable copy.
        return List.copyOf(sent);
    }
}
