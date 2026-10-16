// ADDED LATER: the new requirement "offer drone delivery".
package com.katas.ocp;

// This whole feature is ONE NEW FILE. ShippingQuoter, ShippingMethod and
// the other three methods were not opened, edited, or re-tested.
// That is "open for extension, closed for modification".
public class DroneShipping implements ShippingMethod {

    // The customer-facing name.
    @Override
    public String name() {
        // Shown in the quote.
        return "Drone";
    }

    // 15 plus 4 per kilo: drones charge more for weight.
    @Override
    public int cost(int weightKg) {
        // The drone formula.
        return 15 + weightKg * 4;
    }

    // One day.
    @Override
    public int deliveryDays() {
        // The drone delivery time.
        return 1;
    }
}
