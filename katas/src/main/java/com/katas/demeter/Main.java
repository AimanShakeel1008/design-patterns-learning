// The runnable demo for Lesson 11's Law of Demeter kata.
package com.katas.demeter;

// Shows a reaching paperboy missing the jar, and a polite one collecting.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // NAIVE: 1 EUR in the wallet, 10 EUR in the jar by the door; the paper costs 2 EUR.
        com.katas.demeter.naive.Customer naiveCustomer = new com.katas.demeter.naive.Customer(1, 10);
        // The paperboy reaches into the wallet only, finds 1 EUR, and gives up.
        System.out.println("Naive paperboy collected: " + new com.katas.demeter.naive.Paperboy().collect(naiveCustomer, 2) + " EUR");

        // DEMETER: the same customer situation...
        Customer customer = new Customer(1, 10);
        // ...but the paperboy just asks the customer to pay, and the customer uses the jar.
        System.out.println("Demeter paperboy collected: " + new Paperboy().collect(customer, 2) + " EUR");
        // 11 EUR at the start, 2 paid: 9 left.
        System.out.println("Customer has " + customer.moneyLeft() + " EUR left");
    }
}
