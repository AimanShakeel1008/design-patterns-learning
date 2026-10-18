// The WRONG way (Lesson 11): a wallet that lets anyone count and take its cash.
package com.katas.demeter.naive;

// Holds cash. It trusts whoever holds it to check before taking.
public class Wallet {

    // The cash inside, in EUR.
    private int cash;

    // A wallet starts with some cash.
    public Wallet(int cash) {
        // Store it.
        this.cash = cash;
    }

    // How much is inside: handed to anyone who asks.
    public int getCash() {
        // The amount.
        return cash;
    }

    // Take money out. No check: the caller was supposed to look first.
    public void take(int amount) {
        // Remove it.
        cash -= amount;
    }
}
