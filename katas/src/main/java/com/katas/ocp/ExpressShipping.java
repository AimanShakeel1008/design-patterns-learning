// A second shipping method, as small and self-contained as the first.
package com.katas.ocp;

// Everything about "Express" lives here and nowhere else.
public class ExpressShipping implements ShippingMethod {

    // The customer-facing name.
    @Override
    public String name() {
        // Shown in the quote.
        return "Express";
    }

    // 10 plus 2 per kilo.
    @Override
    public int cost(int weightKg) {
        // The express formula.
        return 10 + weightKg * 2;
    }

    // Two days.
    @Override
    public int deliveryDays() {
        // The express delivery time.
        return 2;
    }
}
