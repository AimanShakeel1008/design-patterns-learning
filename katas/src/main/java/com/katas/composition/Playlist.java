// The real song list. Deliberately written EXACTLY like naive.Playlist
// (addAll reuses add) to prove composition does not care how it is built.
package com.katas.composition;

// The JDK's growable list.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// A playlist that keeps the SongList contract.
public class Playlist implements SongList {

    // The songs, in order.
    private final List<String> songs = new ArrayList<>();

    // Add one song to the end.
    @Override
    public void add(String song) {
        // Store it in the internal list.
        songs.add(song);
    }

    // Add several songs by reusing add(), the same "tidy" style that broke
    // the inheritance version. Here it is harmless: see CountingPlaylist.
    @Override
    public void addAll(List<String> newSongs) {
        // Loop over the incoming songs...
        for (String song : newSongs) {
            // ...and add each. "add" here means THIS Playlist's add, because
            // no subclass exists; a wrapper outside cannot intercept it.
            add(song);
        }
    }

    // How many songs are stored.
    @Override
    public int size() {
        // Ask the internal list.
        return songs.size();
    }
}
