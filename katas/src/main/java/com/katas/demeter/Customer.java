// The Law of Demeter way (Lesson 11): the customer pays; nobody reaches inside.
package com.katas.demeter;

// A newspaper customer that is ASKED to pay and decides for itself where the
// money comes from. Its wallet and coin jar are private and never handed out,
// so adding the jar changed this class only: no caller had to hear about it.
public class Customer {

    // The money the customer carries.
    private final Wallet wallet;

    // Spare money in a jar by the door.
    private final Wallet coinJar;

    // A customer with some cash in the wallet and some in the jar.
    public Customer(int walletCash, int jarCash) {
        // Build the wallet (this object's own field: a friend).
        this.wallet = new Wallet(walletCash);
        // Build the jar (also a friend).
        this.coinJar = new Wallet(jarCash);
    }

    // Pay an amount if possible. Returns true if paid, false if not.
    public boolean pay(int amount) {
        // First choice: the wallet.
        if (wallet.has(amount)) {
            // Take it from there.
            wallet.take(amount);
            // Paid.
            return true;
        }
        // Second choice: the jar by the door.
        if (coinJar.has(amount)) {
            // Take it from there.
            coinJar.take(amount);
            // Paid.
            return true;
        }
        // Neither holds enough: decline, and nothing is taken.
        return false;
    }

    // Total money left, as a plain number (for the demo and tests).
    public int moneyLeft() {
        // Add the two amounts.
        return wallet.cash() + coinJar.cash();
    }
}
