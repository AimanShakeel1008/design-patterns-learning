// A third shipping method.
package com.katas.ocp;

// Everything about "Overnight" lives here and nowhere else.
public class OvernightShipping implements ShippingMethod {

    // The customer-facing name.
    @Override
    public String name() {
        // Shown in the quote.
        return "Overnight";
    }

    // 25 plus 3 per kilo.
    @Override
    public int cost(int weightKg) {
        // The overnight formula.
        return 25 + weightKg * 3;
    }

    // One day.
    @Override
    public int deliveryDays() {
        // The overnight delivery time.
        return 1;
    }
}
