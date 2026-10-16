// The RIGHT WAY, part 1: the variation point, written as a contract.
package com.katas.ocp;

// Everything that DIFFERS between shipping methods lives behind this
// interface. Code that uses it never needs to know which methods exist.
public interface ShippingMethod {

    // The name shown to the customer, e.g. "Express".
    String name();

    // The price for a parcel of this weight, in whole currency units.
    int cost(int weightKg);

    // How many days delivery takes.
    int deliveryDays();
}
