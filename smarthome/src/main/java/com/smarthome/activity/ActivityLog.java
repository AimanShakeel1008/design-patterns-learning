// Activity tracking is its own responsibility, so it gets its own package.
package com.smarthome.activity;

// The JDK's growable list, used for the private storage.
import java.util.ArrayList;
// The JDK's list interface, used in the public method's return type.
import java.util.List;

// The hub's activity history: an ordered record of what happened.
// It knows nothing about devices; it just stores lines of text.
public class ActivityLog {

    // The entries, oldest first. private: the only way in is record().
    private final List<String> entries = new ArrayList<>();

    // Add one line to the end of the history.
    public void record(String entry) {
        // Append it to the internal list.
        entries.add(entry);
    }

    // Read the history. Returns a COPY that cannot be changed, so no caller
    // can quietly delete or rewrite history (encapsulation, Lesson 01).
    public List<String> entries() {
        // List.copyOf makes an unmodifiable snapshot of the current entries.
        return List.copyOf(entries);
    }
}
