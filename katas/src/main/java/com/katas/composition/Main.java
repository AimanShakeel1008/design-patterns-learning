// The runnable demo for Lesson 03's kata.
package com.katas.composition;

// List.of builds a small fixed list in one line.
import java.util.List;

// Runs the same job (add three songs, then read the counter) through the
// inheritance version and the composition version, side by side.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // The same three songs for both versions.
        List<String> songs = List.of("Intro", "Verse", "Outro");

        // INHERITANCE: the subclass, broken by its parent's tidy-up. We write
        // the full package name because both packages have a CountingPlaylist.
        com.katas.composition.naive.CountingPlaylist inherited = new com.katas.composition.naive.CountingPlaylist();
        // Add all three in one call.
        inherited.addAll(songs);
        // The list holds 3 songs, but the counter says 6.
        System.out.println("Inheritance: " + inherited.size() + " songs in the list, counter says "
                + inherited.getAddedCount());

        // COMPOSITION: a counter wrapped around a real Playlist.
        CountingPlaylist composed = new CountingPlaylist(new Playlist());
        // The very same call.
        composed.addAll(songs);
        // 3 songs, counter says 3.
        System.out.println("Composition: " + composed.size() + " songs in the list, counter says "
                + composed.getAddedCount());
    }
}
