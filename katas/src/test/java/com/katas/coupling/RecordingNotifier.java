// A test-only helper, living under src/test so it never ships.
package com.katas.coupling;

// The JDK's growable list.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// A Notifier that delivers nowhere: it just remembers every message, so a
// test can check exactly what Checkout "sent". This is possible ONLY because
// Checkout depends on the Notifier interface rather than on the console.
class RecordingNotifier implements Notifier {

    // Every message received, in order.
    private final List<String> messages = new ArrayList<>();

    // "Deliver" by remembering.
    @Override
    public void send(String message) {
        // Store it for the test to inspect.
        messages.add(message);
    }

    // What has been sent so far, as an unmodifiable snapshot.
    List<String> messages() {
        // A read-only copy.
        return List.copyOf(messages);
    }
}
