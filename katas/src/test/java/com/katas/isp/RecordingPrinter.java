// A test-only stand-in, living under src/test so it never ships.
package com.katas.isp;

// The JDK's growable list.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// Because the client needs only Printer, its fake needs only ONE method.
// With the fat Machine interface it would have had to fake scan and fax too.
class RecordingPrinter implements Printer {

    // Every document received, in order.
    private final List<String> received = new ArrayList<>();

    // Remember the document instead of printing it.
    @Override
    public String print(String document) {
        // Record it for the test.
        received.add(document);
        // Answer the way a printer would.
        return "Recorded: " + document;
    }

    // What the client sent, as a read-only snapshot.
    List<String> received() {
        // An unmodifiable copy.
        return List.copyOf(received);
    }
}
