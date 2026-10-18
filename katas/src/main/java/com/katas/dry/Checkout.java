// The DRY way (Lesson 11): the checkout asks the same rule's one home.
package com.katas.dry;

// Works out what the shopper pays for delivery, by asking the shared policy.
public class Checkout {

    // The same policy object the banner uses.
    private final DeliveryPolicy policy;

    // The policy is handed in from outside (the Main demo, or a test).
    public Checkout(DeliveryPolicy policy) {
        // Remember it.
        this.policy = policy;
    }

    // The delivery fee, in EUR, for a basket worth totalEuros.
    public int deliveryFee(int totalEuros) {
        // No number typed here: the policy decides.
        return policy.feeFor(totalEuros);
    }
}
