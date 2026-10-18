// The WRONG way (Lesson 11): the second hand-written copy of the same rule.
package com.katas.dry.naive;

// Works out what the shopper actually pays for delivery.
// COPY 2 of the free-delivery rule lives here, and this one WAS updated to
// the new 60 EUR threshold. Nothing in the code links it to CartBanner's copy.
public class Checkout {

    // The delivery fee, in EUR, for a basket worth totalEuros.
    public int deliveryFee(int totalEuros) {
        // The rule, copy 2: free from 60 EUR (updated), otherwise a 5 EUR fee.
        return totalEuros >= 60 ? 0 : 5;
    }
}
