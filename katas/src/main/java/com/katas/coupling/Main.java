// The runnable demo for Lesson 04's kata.
package com.katas.coupling;

// List.of for building the menu's item list.
import java.util.List;

// Builds the same order twice: once through the naive all-in-one class, once
// through the small cohesive classes. The output is identical; the structure
// is what differs, and that is what the lesson measures.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // NAIVE: one class does everything. Full name, because the class lives
        // in the naive sub-package.
        com.katas.coupling.naive.OrderManager manager = new com.katas.coupling.naive.OrderManager();
        // Two margheritas...
        manager.add("Margherita", 2);
        // ...and one cola...
        manager.add("Cola", 1);
        // ...announced straight to the console.
        manager.placeOrder();

        // COHESIVE AND LOOSELY COUPLED: each class does one job.
        // The items exist on their own, before any menu or order.
        MenuItem margherita = new Pizza("Margherita", 9);
        // A drink item.
        MenuItem cola = new Drink("Cola", 3);
        // The menu gathers existing items (aggregation).
        Menu menu = new Menu(List.of(margherita, cola));
        // A fresh order.
        Order order = new Order();
        // The order creates its own lines (composition).
        order.add(menu.find("Margherita"), 2);
        // A second line.
        order.add(menu.find("Cola"), 1);
        // The channel is chosen HERE, not inside Checkout.
        Checkout checkout = new Checkout(new ConsoleNotifier());
        // Same announcement as the naive version.
        checkout.place(order);
    }
}
