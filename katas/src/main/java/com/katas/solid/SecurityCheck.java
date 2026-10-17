// The refactored policy: all five principles applied.
package com.katas.solid;

// The JDK's growable list.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// ONE job (SRP): decide which problems to report, and report them. It asks
// each watched thing about itself (OCP: no ladder), needs only the small
// Monitored role (ISP), depends only on abstractions injected through the
// constructor (DIP), and "quiet hours" is a SETTING rather than a subclass
// that refuses work (LSP: there is nothing left to substitute badly).
public class SecurityCheck {

    // What to watch, through the small role interface.
    private final List<Monitored> watched;

    // Where alerts go: any channel keeping the contract.
    private final AlertChannel channel;

    // How alerts are worded.
    private final AlertFormatter formatter;

    // If true, non-urgent problems are held back at night (smoke never is).
    private final boolean quietAtNight;

    // CONSTRUCTOR INJECTION: everything arrives from the composition root.
    public SecurityCheck(List<Monitored> watched, AlertChannel channel, AlertFormatter formatter,
                         boolean quietAtNight) {
        // An unmodifiable copy of the watched things.
        this.watched = List.copyOf(watched);
        // Remember the channel.
        this.channel = channel;
        // Remember the formatter.
        this.formatter = formatter;
        // Remember the setting.
        this.quietAtNight = quietAtNight;
    }

    // Check everything once. Returns the alerts actually sent, so callers (and
    // tests) can see what happened without a history inside this class.
    public List<String> run(boolean nightTime) {
        // The alerts sent during this run.
        List<String> sent = new ArrayList<>();
        // Ask every watched thing about itself.
        for (Monitored item : watched) {
            // Nothing wrong: move on.
            if (!item.needsAttention()) {
                // Next item.
                continue;
            }
            // Quiet hours hold back the non-urgent problems only.
            if (nightTime && quietAtNight && !item.isUrgent()) {
                // Next item.
                continue;
            }
            // Word it...
            String message = formatter.format(item.problem(), nightTime);
            // ...deliver it...
            channel.send(message);
            // ...and report it back to the caller.
            sent.add(message);
        }
        // An unmodifiable copy for the caller.
        return List.copyOf(sent);
    }
}
