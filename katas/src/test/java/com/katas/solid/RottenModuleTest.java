// Sits beside the refactored module; reaches the rotten classes by full name.
package com.katas.solid;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the device list.
import java.util.List;

// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;

// PINS THE SYMPTOMS on purpose: two of the rotten module's failures, which
// only show up when the code runs.
class RottenModuleTest {

    // The rotten house: an unlocked door, smoke in the kitchen.
    private final List<com.katas.solid.naive.HomeDevice> house = List.of(
            // Unlocked.
            new com.katas.solid.naive.DoorLock("Front Door", false),
            // Smoke.
            new com.katas.solid.naive.SmokeAlarm("Kitchen", true));

    // LSP: the "quiet" subclass refuses the parent's job at night.
    @Test
    void quietSubclassRefusesToCheckAtNight() {
        // A caller trusting the parent's promise is broken.
        assertThrows(UnsupportedOperationException.class,
                () -> new com.katas.solid.naive.QuietSecurityModule(house, "email").checkHouse(true));
    }

    // OCP/DIP: a channel picked by a magic string fails only at run time.
    @Test
    void misspelledChannelOnlyFailsWhenAnAlertIsSent() {
        // "emial" compiles fine; the mistake surfaces at the first alert.
        assertThrows(IllegalArgumentException.class,
                () -> new com.katas.solid.naive.SecurityModule(house, "emial").checkHouse(false));
    }
}
