// One shipping method: its name, price rule and delivery time, in one place.
package com.katas.ocp;

// Everything about "Standard" lives here and nowhere else.
public class StandardShipping implements ShippingMethod {

    // The customer-facing name.
    @Override
    public String name() {
        // Shown in the quote.
        return "Standard";
    }

    // 5 plus 1 per kilo.
    @Override
    public int cost(int weightKg) {
        // The standard formula.
        return 5 + weightKg;
    }

    // Five days.
    @Override
    public int deliveryDays() {
        // The standard delivery time.
        return 5;
    }
}
