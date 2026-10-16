// One kind of menu item.
package com.katas.coupling;

// A Pizza IS a MenuItem: the INHERITANCE arrow in the class diagram.
public class Pizza extends MenuItem {

    // Build a pizza with its name and price.
    public Pizza(String name, int price) {
        // Let MenuItem store both.
        super(name, price);
    }

    // Pizzas go in the pizza section.
    @Override
    public String category() {
        // The section name.
        return "pizza";
    }
}
