// The Law of Demeter way (Lesson 11): a wallet only its owner handles.
package com.katas.demeter;

// Holds cash and guards its own rule: never take more than is inside.
public class Wallet {

    // The cash inside, in EUR.
    private int cash;

    // A wallet starts with some cash.
    public Wallet(int cash) {
        // Store it.
        this.cash = cash;
    }

    // Is there at least this much inside?
    public boolean has(int amount) {
        // Compare.
        return cash >= amount;
    }

    // Take money out, refusing to go below zero (the invariant).
    public void take(int amount) {
        // Not enough: refuse loudly.
        if (!has(amount)) {
            // Name the problem.
            throw new IllegalArgumentException("Only " + cash + " EUR in here, asked for " + amount);
        }
        // Remove it.
        cash -= amount;
    }

    // How much is left, as a plain number (a value, not an object to reach into).
    public int cash() {
        // The amount.
        return cash;
    }
}
