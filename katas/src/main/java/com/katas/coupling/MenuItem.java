// The kata for Lesson 04: small, cohesive classes, loosely coupled.
package com.katas.coupling;

// Anything that can appear on a menu. Abstract: every real item is a more
// specific kind (Pizza, Drink). In the class diagram this is the parent at
// the top of the INHERITANCE arrows.
public abstract class MenuItem {

    // The dish's name, e.g. "Margherita".
    private final String name;

    // Its price in whole currency units.
    private final int price;

    // Only subclasses build menu items, via super(name, price).
    protected MenuItem(String name, int price) {
        // Store the name.
        this.name = name;
        // Store the price.
        this.price = price;
    }

    // Read the name.
    public String getName() {
        // Hand back the private field.
        return name;
    }

    // Read the price.
    public int getPrice() {
        // Hand back the private field.
        return price;
    }

    // Each kind of item says which section of the menu it belongs to.
    public abstract String category();
}
