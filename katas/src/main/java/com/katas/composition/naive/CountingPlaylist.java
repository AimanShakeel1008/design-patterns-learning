// WRONG WAY: extending a class we do not control, to add a feature.
package com.katas.composition.naive;

// The interface type for the incoming list of songs.
import java.util.List;

// A playlist that also counts how many songs were ever added. It looked
// correct when written. It was broken later by a change to Playlist alone:
// the fragile base class problem.
public class CountingPlaylist extends Playlist {

    // How many songs have been added, ever.
    private int addedCount;

    // Count one, then let the parent store it.
    @Override
    public void add(String song) {
        // One more song added.
        addedCount++;
        // Let Playlist do the actual storing.
        super.add(song);
    }

    // Count the whole batch, then let the parent store it.
    @Override
    public void addAll(List<String> newSongs) {
        // The whole batch counted at once...
        addedCount += newSongs.size();
        // ...but Playlist.addAll now calls add() for each song, and add() is
        // OUR override above, so every song gets counted a SECOND time.
        super.addAll(newSongs);
    }

    // Report the count.
    public int getAddedCount() {
        // After addAll of 3 songs this returns 6, not 3.
        return addedCount;
    }
}
