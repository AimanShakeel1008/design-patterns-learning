// The WRONG way (Lesson 11): the paperboy reaches into the customer's wallet.
package com.katas.demeter.naive;

// Collects the weekly newspaper money. Written before the coin jar existed,
// and it talks to a STRANGER (the wallet) instead of its friend (the customer).
public class Paperboy {

    // Collect the price from a customer; returns the EUR actually collected.
    public int collect(Customer customer, int price) {
        // TRAIN WRECK: customer.getWallet().getCash() reaches two objects deep.
        if (customer.getWallet().getCash() >= price) {
            // And a second reach-through, to take the money himself.
            customer.getWallet().take(price);
            // Collected in full.
            return price;
        }
        // It never heard about the jar, so a customer who could pay is turned away.
        return 0;
    }
}
