// An order: one job, keeping its lines and adding them up. High cohesion.
package com.katas.coupling;

// The JDK's growable list.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// An Order CREATES its OrderLines itself and never hands them out, so the
// lines live and die with the order. That strong ownership is COMPOSITION
// in the diagram (the filled diamond).
public class Order {

    // The lines, created and owned by this order alone.
    private final List<OrderLine> lines = new ArrayList<>();

    // Add an item: the ORDER builds the line, so nobody else owns it.
    public void add(MenuItem item, int quantity) {
        // Create the part inside the whole.
        lines.add(new OrderLine(item, quantity));
    }

    // Total number of items across all lines.
    public int itemCount() {
        // Start from zero...
        int count = 0;
        // ...and add each line's quantity.
        for (OrderLine line : lines) {
            // Accumulate.
            count += line.quantity();
        }
        // The total item count.
        return count;
    }

    // Total price across all lines.
    public int total() {
        // Start from zero...
        int sum = 0;
        // ...and ask each line for its subtotal.
        for (OrderLine line : lines) {
            // Accumulate.
            sum += line.subtotal();
        }
        // The order's total.
        return sum;
    }
}
