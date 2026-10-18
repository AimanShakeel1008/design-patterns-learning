// The DRY way (Lesson 11): the banner asks the rule's one home.
package com.katas.dry;

// Shows the shopper how close the basket is to free delivery. It owns the
// WORDING only; the rule itself is asked of the shared DeliveryPolicy.
public class CartBanner {

    // The one shared copy of the rule.
    private final DeliveryPolicy policy;

    // The policy is handed in, so banner and checkout can share the same one.
    public CartBanner(DeliveryPolicy policy) {
        // Remember it.
        this.policy = policy;
    }

    // The banner text for a basket worth totalEuros.
    public String message(int totalEuros) {
        // Ask the policy instead of comparing against a number typed here.
        if (policy.isFree(totalEuros)) {
            // Over the line: celebrate.
            return "Free delivery!";
        }
        // Under the line: the policy also knows how much is missing.
        return "Add " + policy.missing(totalEuros) + " EUR for free delivery";
    }
}
