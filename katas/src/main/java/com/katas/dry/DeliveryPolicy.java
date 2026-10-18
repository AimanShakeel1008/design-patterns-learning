// The DRY way (Lesson 11): the rule gets one home.
package com.katas.dry;

// The ONE place that knows the free-delivery rule: the threshold and the fee
// are written here and nowhere else (DRY: every piece of knowledge has a single
// home). Changing the shop's policy is now one edit, and every screen that
// asks this object follows automatically.
public class DeliveryPolicy {

    // From this basket value (in EUR) delivery is free.
    private final int freeFromEuros;

    // The fee (in EUR) charged below that value.
    private final int feeEuros;

    // The shop chooses the numbers once, when the policy is built.
    public DeliveryPolicy(int freeFromEuros, int feeEuros) {
        // Remember the threshold.
        this.freeFromEuros = freeFromEuros;
        // Remember the fee.
        this.feeEuros = feeEuros;
    }

    // Is delivery free for a basket worth totalEuros? The comparison is written once.
    public boolean isFree(int totalEuros) {
        // The single copy of the rule.
        return totalEuros >= freeFromEuros;
    }

    // How many EUR the shopper must still add for free delivery (0 if already free).
    public int missing(int totalEuros) {
        // Reuse isFree rather than repeating the comparison.
        return isFree(totalEuros) ? 0 : freeFromEuros - totalEuros;
    }

    // The delivery fee to charge for a basket worth totalEuros.
    public int feeFor(int totalEuros) {
        // Reuse isFree again: one rule, asked three ways.
        return isFree(totalEuros) ? 0 : feeEuros;
    }
}
