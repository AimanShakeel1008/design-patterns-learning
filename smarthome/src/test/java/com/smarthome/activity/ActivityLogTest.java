// Same package as ActivityLog.
package com.smarthome.activity;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the expected entries.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;

// Proves the log keeps order and cannot be rewritten from outside.
class ActivityLogTest {

    // Entries come back in the order they were recorded.
    @Test
    void recordsEntriesInOrder() {
        // A fresh, empty log.
        ActivityLog log = new ActivityLog();
        // Two events, in order.
        log.record("first");
        log.record("second");
        // Same order back.
        assertEquals(List.of("first", "second"), log.entries());
    }

    // The returned list is a read-only snapshot.
    @Test
    void entriesCannotBeChangedFromOutside() {
        // A log with one entry.
        ActivityLog log = new ActivityLog();
        log.record("real event");
        // Try to sneak a fake entry into the returned list: it must refuse.
        assertThrows(UnsupportedOperationException.class, () -> log.entries().add("fake event"));
        // The log itself still holds only the real event.
        assertEquals(List.of("real event"), log.entries());
    }
}
