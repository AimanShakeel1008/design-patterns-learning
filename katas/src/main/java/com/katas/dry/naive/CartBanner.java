// The WRONG way (Lesson 11): one business rule written out by hand in two places.
package com.katas.dry.naive;

// Shows the shopper how close the basket is to free delivery.
// COPY 1 of the free-delivery rule lives here. When the shop raised the
// threshold from 50 to 60 EUR, Checkout was updated and this copy was missed,
// so the two screens now disagree. Kept frozen to show that pain.
public class CartBanner {

    // The banner text for a basket worth totalEuros.
    public String message(int totalEuros) {
        // The rule, copy 1: free delivery from 50 EUR (the OLD number, never updated).
        if (totalEuros >= 50) {
            // Over the line: celebrate.
            return "Free delivery!";
        }
        // Under the line: the same number typed a second time, to say what is missing.
        return "Add " + (50 - totalEuros) + " EUR for free delivery";
    }
}
