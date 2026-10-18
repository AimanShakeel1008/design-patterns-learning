// The WRONG way (Lesson 11): a customer who hands its insides to strangers.
package com.katas.demeter.naive;

// A newspaper customer. It exposes its wallet with a getter, so other classes
// reach through it and handle the money themselves. Later the customer started
// keeping spare cash in a coin jar by the door (modelled as a second Wallet):
// a change to the customer's INSIDES that every reaching class must now hear about.
public class Customer {

    // The money the customer carries.
    private final Wallet wallet;

    // ADDED LATER: spare money in a jar by the door.
    private final Wallet coinJar;

    // A customer with some cash in the wallet and some in the jar.
    public Customer(int walletCash, int jarCash) {
        // Build the wallet.
        this.wallet = new Wallet(walletCash);
        // Build the jar.
        this.coinJar = new Wallet(jarCash);
    }

    // Hands the wallet itself to anyone who asks: strangers can now reach inside.
    public Wallet getWallet() {
        // The very object, not a copy.
        return wallet;
    }

    // The new insides, exposed the same way. Only callers who know about it look here.
    public Wallet getCoinJar() {
        // The very object.
        return coinJar;
    }
}
