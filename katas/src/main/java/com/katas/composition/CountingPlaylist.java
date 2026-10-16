// The RIGHT WAY: add a feature by HOLDING a song list, not by extending one.
package com.katas.composition;

// The interface type for a batch of songs.
import java.util.List;

// A CountingPlaylist HAS A SongList inside it (composition) and passes each
// request on to it (delegation), counting along the way. It never extends
// Playlist, so Playlist's internal habits cannot reach back into it.
public class CountingPlaylist implements SongList {

    // The object we wrap and forward to. Typed by the CONTRACT, so any
    // SongList works: a Playlist today, something else tomorrow.
    private final SongList inner;

    // How many songs have been added, ever.
    private int addedCount;

    // The wrapped list is handed in from outside, so the caller chooses it.
    public CountingPlaylist(SongList inner) {
        // Keep a reference to the object we will delegate to.
        this.inner = inner;
    }

    // Count one, then forward.
    @Override
    public void add(String song) {
        // One more song added.
        addedCount++;
        // Delegate the real work to the wrapped list.
        inner.add(song);
    }

    // Count the batch, then forward.
    @Override
    public void addAll(List<String> newSongs) {
        // The whole batch counted once.
        addedCount += newSongs.size();
        // inner.addAll runs Playlist's code; when THAT code calls add(), it
        // means Playlist's own add, not ours. Nothing comes back here, so
        // nothing is counted twice.
        inner.addAll(newSongs);
    }

    // Forward the question: we store nothing ourselves.
    @Override
    public int size() {
        // The wrapped list knows its own size.
        return inner.size();
    }

    // The one thing this wrapper adds.
    public int getAddedCount() {
        // After addAll of 3 songs this returns 3.
        return addedCount;
    }
}
