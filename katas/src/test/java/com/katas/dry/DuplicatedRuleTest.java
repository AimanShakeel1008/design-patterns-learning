// Same package as the DRY kata.
package com.katas.dry;

// The two hand-written copies of the rule, kept frozen to show the pain.
import com.katas.dry.naive.CartBanner;
import com.katas.dry.naive.Checkout;
// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Pins the naive version's bug, so the pain is a fact anyone can re-run.
class DuplicatedRuleTest {

    // After a change made in only one copy, the two screens contradict each other.
    @Test
    void theCopiesDisagreeAfterAHalfDoneChange() {
        // A 55 EUR basket: the banner (old copy, 50 EUR) promises free delivery...
        assertEquals("Free delivery!", new CartBanner().message(55));
        // ...but the checkout (new copy, 60 EUR) still charges the fee.
        assertEquals(5, new Checkout().deliveryFee(55));
    }
}
