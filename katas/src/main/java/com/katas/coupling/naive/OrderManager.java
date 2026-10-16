// WRONG-WAY VERSION, kept on purpose: low cohesion and tight coupling.
package com.katas.coupling.naive;

// One class doing three unrelated jobs: knowing the menu's prices, keeping
// the order's running total, and announcing the order. Any change to ANY of
// those jobs means editing this one class. And it is welded to the console.
public class OrderManager {

    // Running total of the order, in whole currency units.
    private int total;

    // How many items have been ordered.
    private int itemCount;

    // JOB 1 (menu pricing) and JOB 2 (order keeping) tangled in one method.
    public void add(String itemName, int quantity) {
        // The menu lives HERE, as an if-else ladder: a new dish means editing
        // order code, and no other class can reuse these prices.
        int price;
        // Look the price up by name...
        if (itemName.equals("Margherita")) {
            // ...a margherita costs 9...
            price = 9;
        } else if (itemName.equals("Pepperoni")) {
            // ...a pepperoni costs 11...
            price = 11;
        } else if (itemName.equals("Cola")) {
            // ...a cola costs 3...
            price = 3;
        } else {
            // ...and anything else is refused.
            throw new IllegalArgumentException("Unknown item: " + itemName);
        }
        // Order keeping: add to the running total...
        total += price * quantity;
        // ...and to the item count.
        itemCount += quantity;
    }

    // JOB 3 (announcing), welded to System.out. Sending a text message instead
    // means editing this class, and a test cannot easily check what was "sent".
    public void placeOrder() {
        // Hard-wired output channel: tight coupling to the console.
        System.out.println("Order placed: " + itemCount + " items, total " + total);
    }
}
