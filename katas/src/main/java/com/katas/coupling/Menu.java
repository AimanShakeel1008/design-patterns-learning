// The menu: one job, finding items by name. High cohesion.
package com.katas.coupling;

// The JDK's list interface.
import java.util.List;

// A Menu GATHERS menu items that are created elsewhere and can live on
// without it (the same Cola could be on the lunch and the dinner menu).
// That "whole made of independent parts" is AGGREGATION in the diagram.
public class Menu {

    // The items on this menu. A private snapshot, so outsiders cannot edit it.
    private final List<MenuItem> items;

    // The items are handed IN: the menu did not create them and does not own them.
    public Menu(List<MenuItem> items) {
        // Keep an unmodifiable copy of the list (the items themselves are shared).
        this.items = List.copyOf(items);
    }

    // Find an item by its exact name.
    public MenuItem find(String name) {
        // Check each item in turn...
        for (MenuItem item : items) {
            // ...and return the first whose name matches.
            if (item.getName().equals(name)) {
                // Found it.
                return item;
            }
        }
        // Nothing matched: refuse loudly, naming what was asked for.
        throw new IllegalArgumentException("Not on the menu: " + name);
    }
}
