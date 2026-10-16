// WRONG-WAY VERSION, kept on purpose: the growing if-else chain.
package com.katas.ocp.naive;

// Quotes shipping prices by checking the method's NAME in if-else ladders.
// Every new shipping method means opening this class and editing it, in TWO
// places, because the same ladder had to be written twice.
public class ShippingQuoter {

    // One line of text for a customer, e.g. "Express: 20 (2-day delivery)".
    public String quote(String method, int weightKg) {
        // Both private ladders below must know about every method.
        return method + ": " + cost(method, weightKg) + " (" + days(method) + "-day delivery)";
    }

    // LADDER 1: price rules, one branch per method.
    private int cost(String method, int weightKg) {
        // Standard: 5 plus 1 per kilo.
        if (method.equals("Standard")) {
            // The standard formula.
            return 5 + weightKg;
        } else if (method.equals("Express")) {
            // Express: 10 plus 2 per kilo.
            return 10 + weightKg * 2;
        } else if (method.equals("Overnight")) {
            // Overnight: 25 plus 3 per kilo.
            return 25 + weightKg * 3;
        }
        // Anything else was never written into this ladder.
        throw new IllegalArgumentException("Unknown method: " + method);
    }

    // LADDER 2: the same branches AGAIN, for delivery time. Forget to update
    // one ladder when adding a method, and quote() fails halfway through.
    private int days(String method) {
        // Standard takes 5 days.
        if (method.equals("Standard")) {
            // Five days.
            return 5;
        } else if (method.equals("Express")) {
            // Two days.
            return 2;
        } else if (method.equals("Overnight")) {
            // One day.
            return 1;
        }
        // The second place a new method would have to be added.
        throw new IllegalArgumentException("Unknown method: " + method);
    }
}
