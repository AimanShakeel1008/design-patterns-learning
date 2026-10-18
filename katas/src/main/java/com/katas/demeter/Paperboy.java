// The Law of Demeter way (Lesson 11): the paperboy talks only to its friend.
package com.katas.demeter;

// Collects the weekly newspaper money by ASKING the customer to pay. It calls
// a method on its parameter (a friend) and never on anything the customer
// holds (a stranger), so it never needs to know about wallets or jars.
public class Paperboy {

    // Collect the price from a customer; returns the EUR actually collected.
    public int collect(Customer customer, int price) {
        // One call on a friend. How the customer pays is the customer's business.
        return customer.pay(price) ? price : 0;
    }
}
