// The RIGHT WAY, part 2: the CLOSED code.
package com.katas.ocp;

// The JDK's growable list.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// Quotes any shipping method it is given. It names no concrete method and
// contains no if-else on method names, so new methods never require
// editing it. It is closed for modification, and open for extension
// through the ShippingMethod contract.
public class ShippingQuoter {

    // One line of text for a customer, e.g. "Express: 20 (2-day delivery)".
    public String quote(ShippingMethod method, int weightKg) {
        // Ask the method itself: no ladder, no knowledge of which method it is.
        return method.name() + ": " + method.cost(weightKg) + " (" + method.deliveryDays() + "-day delivery)";
    }

    // Quote every method on offer, in the order given.
    public List<String> quoteAll(List<ShippingMethod> methods, int weightKg) {
        // Collect one line per method.
        List<String> lines = new ArrayList<>();
        // The same call for every method, whatever it is.
        for (ShippingMethod method : methods) {
            // Reuse the single-quote rule.
            lines.add(quote(method, weightKg));
        }
        // An unmodifiable copy for the caller.
        return List.copyOf(lines);
    }
}
