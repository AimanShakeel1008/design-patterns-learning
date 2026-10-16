// WRONG-WAY setup, kept on purpose: a parent class that a subclass extends.
package com.katas.composition.naive;

// ArrayList: the JDK's growable list (an implementation of List).
import java.util.ArrayList;
// List: the JDK's interface for an ordered collection.
import java.util.List;

// A simple playlist. This is the parent AFTER a "harmless" tidy-up by its
// author: addAll() now reuses add() instead of adding to the list directly.
// Nothing about Playlist's own behaviour changed, yet that tidy-up breaks
// naive.CountingPlaylist, a subclass its author never even looked at.
public class Playlist {

    // The songs, in order. List<String> means "a list that holds text".
    private final List<String> songs = new ArrayList<>();

    // Add one song to the end of the playlist.
    public void add(String song) {
        // Store it in the internal list.
        songs.add(song);
    }

    // Add several songs. THE TIDY-UP: it now calls add() for each song.
    // Because add() can be overridden, this line quietly hands control back
    // to whatever subclass object this really is.
    public void addAll(List<String> newSongs) {
        // Loop over the incoming songs...
        for (String song : newSongs) {
            // ...and reuse add(). Before the tidy-up this line was
            // songs.add(song), which no subclass could intercept.
            add(song);
        }
    }

    // How many songs are in the playlist.
    public int size() {
        // Ask the internal list for its length.
        return songs.size();
    }
}
