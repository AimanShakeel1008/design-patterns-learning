// The test sits in the composition package; it reaches the naive classes by import.
package com.katas.composition;

// The broken, inheritance-based counter.
import com.katas.composition.naive.CountingPlaylist;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the test input.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// PINS THE SYMPTOM on purpose. This test passes when the bug is present, so
// the lesson's claim ("the subclass counts 6") is checked by the machine,
// not just asserted in prose.
class FragileBaseClassTest {

    // The parent's tidy-up makes the subclass count every song twice.
    @Test
    void inheritedCounterDoubleCountsAfterTheParentChanged() {
        // The subclass from the naive package.
        CountingPlaylist playlist = new CountingPlaylist();
        // Three songs in one batch.
        playlist.addAll(List.of("Intro", "Verse", "Outro"));
        // The list is right: three songs are stored...
        assertEquals(3, playlist.size());
        // ...but the counter is wrong: 3 from addAll + 3 from add = 6.
        assertEquals(6, playlist.getAddedCount());
    }
}
