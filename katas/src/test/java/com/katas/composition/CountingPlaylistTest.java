// Same package as the composition classes under test.
package com.katas.composition;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for small test inputs.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves the COMPOSED counter is correct, and that it works around any SongList.
class CountingPlaylistTest {

    // The case that broke the inheritance version: a batch add.
    @Test
    void addAllCountsEachSongOnce() {
        // A counter wrapped around a real Playlist.
        CountingPlaylist playlist = new CountingPlaylist(new Playlist());
        // Add three songs in one call.
        playlist.addAll(List.of("Intro", "Verse", "Outro"));
        // Three songs stored...
        assertEquals(3, playlist.size());
        // ...and three counted, not six.
        assertEquals(3, playlist.getAddedCount());
    }

    // Single adds and batch adds mix correctly.
    @Test
    void singleAndBatchAddsAddUp() {
        // A fresh wrapped playlist.
        CountingPlaylist playlist = new CountingPlaylist(new Playlist());
        // One song on its own...
        playlist.add("Intro");
        // ...then two in a batch.
        playlist.addAll(List.of("Verse", "Outro"));
        // 1 + 2 = 3 counted.
        assertEquals(3, playlist.getAddedCount());
    }

    // Because the wrapper is itself a SongList, wrappers can wrap wrappers.
    @Test
    void wrapperCanWrapAnyImplementationOfTheContract() {
        // The inner counter wraps a Playlist...
        CountingPlaylist inner = new CountingPlaylist(new Playlist());
        // ...and the outer counter wraps the inner counter.
        CountingPlaylist outer = new CountingPlaylist(inner);
        // One batch through the outermost object.
        outer.addAll(List.of("Intro", "Verse"));
        // Each layer counted the batch exactly once.
        assertEquals(2, outer.getAddedCount());
        // The request really travelled through to the inner layer.
        assertEquals(2, inner.getAddedCount());
    }
}
