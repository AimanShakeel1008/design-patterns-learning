// Same package as the DRY classes under test.
package com.katas.dry;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves DRY's payoff: with one home for the rule, the screens can never disagree.
class DeliveryPolicyTest {

    // At 55 and 60 EUR, the banner's promise and the checkout's fee match.
    @Test
    void bannerAndCheckoutAlwaysAgree() {
        // One policy, shared by both screens.
        DeliveryPolicy policy = new DeliveryPolicy(60, 5);
        // The banner, asking that policy.
        CartBanner banner = new CartBanner(policy);
        // The checkout, asking the very same policy.
        Checkout checkout = new Checkout(policy);
        // 55 EUR: not free, 5 missing...
        assertEquals("Add 5 EUR for free delivery", banner.message(55));
        // ...and the fee is charged.
        assertEquals(5, checkout.deliveryFee(55));
        // 60 EUR: free...
        assertEquals("Free delivery!", banner.message(60));
        // ...and nothing is charged.
        assertEquals(0, checkout.deliveryFee(60));
    }

    // Changing the rule in its one home moves every screen at once.
    @Test
    void oneEditMovesEveryScreen() {
        // A different shop policy: free from 40 EUR, otherwise 3 EUR.
        DeliveryPolicy policy = new DeliveryPolicy(40, 3);
        // Both screens built on it.
        CartBanner banner = new CartBanner(policy);
        Checkout checkout = new Checkout(policy);
        // 45 EUR is now over the line on both screens.
        assertEquals("Free delivery!", banner.message(45));
        assertEquals(0, checkout.deliveryFee(45));
        // 30 EUR is 10 short, and the new fee applies.
        assertEquals("Add 10 EUR for free delivery", banner.message(30));
        assertEquals(3, checkout.deliveryFee(30));
    }
}
