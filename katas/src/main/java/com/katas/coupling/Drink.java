// Another kind of menu item.
package com.katas.coupling;

// A Drink IS a MenuItem: a second INHERITANCE arrow.
public class Drink extends MenuItem {

    // Build a drink with its name and price.
    public Drink(String name, int price) {
        // Let MenuItem store both.
        super(name, price);
    }

    // Drinks go in the drinks section.
    @Override
    public String category() {
        // The section name.
        return "drink";
    }
}
