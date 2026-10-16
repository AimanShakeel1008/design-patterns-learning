// The contract that lets a wrapper stand in for the real thing.
package com.katas.composition;

// The interface type for a batch of songs.
import java.util.List;

// What any song list can do. Both the real Playlist and the CountingPlaylist
// that wraps it keep this contract, so clients cannot tell them apart.
public interface SongList {

    // Add one song.
    void add(String song);

    // Add several songs.
    void addAll(List<String> newSongs);

    // How many songs are in the list.
    int size();
}
