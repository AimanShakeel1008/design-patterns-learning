// A test-only shipping method, living under src/test so it never ships.
package com.katas.ocp;

// Proof that the quoter is open for extension even from OUTSIDE the main
// code: a test can invent a brand-new method and the quoter just works.
class PickupInStore implements ShippingMethod {

    // The customer-facing name.
    @Override
    public String name() {
        // Shown in the quote.
        return "Pickup";
    }

    // Collecting it yourself is free, whatever it weighs.
    @Override
    public int cost(int weightKg) {
        // Always zero.
        return 0;
    }

    // Ready the next day.
    @Override
    public int deliveryDays() {
        // One day.
        return 1;
    }
}
