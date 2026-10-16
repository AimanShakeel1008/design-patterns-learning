// The runnable demo for Lesson 06's kata.
package com.katas.ocp;

// List.of to build the list of methods on offer.
import java.util.List;

// Shows the naive quoter refusing the new "Drone" method, then the
// open/closed quoter handling it without a single edit.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // NAIVE: it only knows the names written into its ladders.
        com.katas.ocp.naive.ShippingQuoter naive = new com.katas.ocp.naive.ShippingQuoter();
        // A known method works.
        System.out.println("Naive: " + naive.quote("Express", 5));
        // try: the new method will be refused by throwing.
        try {
            // "Drone" is in neither ladder.
            System.out.println("Naive: " + naive.quote("Drone", 5));
        } catch (IllegalArgumentException e) {
            // Supporting it would mean editing BOTH ladders.
            System.out.println("Naive refused: " + e.getMessage());
        }

        // OPEN/CLOSED: the methods on offer are chosen HERE, as objects.
        List<ShippingMethod> offered = List.of(new StandardShipping(), new ExpressShipping(),
                new OvernightShipping(), new DroneShipping());
        // The quoter has never heard of Drone, and does not need to.
        for (String line : new ShippingQuoter().quoteAll(offered, 5)) {
            // One line per method.
            System.out.println("OCP: " + line);
        }
    }
}
