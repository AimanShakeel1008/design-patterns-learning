// The runnable demo for Lesson 11's DRY kata.
package com.katas.dry;

// Shows the half-updated copies disagreeing, then one shared rule agreeing.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // NAIVE: the banner still has the old 50 EUR copy of the rule...
        System.out.println("Naive banner:   " + new com.katas.dry.naive.CartBanner().message(55));
        // ...while the checkout has the new 60 EUR copy, so a 55 EUR basket is charged anyway.
        System.out.println("Naive checkout: fee " + new com.katas.dry.naive.Checkout().deliveryFee(55) + " EUR");

        // DRY: the rule is written once, with the new threshold.
        DeliveryPolicy policy = new DeliveryPolicy(60, 5);
        // The banner and the checkout share that ONE policy object.
        CartBanner banner = new CartBanner(policy);
        // Same object handed to the checkout.
        Checkout checkout = new Checkout(policy);
        // 55 EUR: both screens agree that delivery is not free yet.
        System.out.println("DRY banner:     " + banner.message(55));
        // And the checkout charges the fee the banner warned about.
        System.out.println("DRY checkout:   fee " + checkout.deliveryFee(55) + " EUR");
        // 60 EUR: both agree that it is free.
        System.out.println("DRY banner:     " + banner.message(60));
        // And nothing is charged.
        System.out.println("DRY checkout:   fee " + checkout.deliveryFee(60) + " EUR");
    }
}
