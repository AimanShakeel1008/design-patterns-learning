package com.katas.oopbasics;

// A tiny runnable demo that SHOWS the two big ideas of Lesson 01 in action:
// (1) encapsulation guarding a bank account's rule, and (2) an interface letting
// one loop drive two totally different devices.
public class Main {

    public static void main(String[] args) {

        // ---- PART 1: ENCAPSULATION ----------------------------------------
        System.out.println("== Encapsulation: the account guards its own rule ==");

        // Build a valid account with £100. The constructor accepted it because
        // 100 is not negative.
        BankAccount account = new BankAccount(100);

        // Ask the object for its balance through the read-only window.
        System.out.println("Opening balance: " + account.balance());

        // A normal deposit: the deposit() method checks the amount, then updates
        // the protected field. We never touch the field ourselves.
        account.deposit(50);
        System.out.println("After depositing 50: " + account.balance());

        // A normal withdrawal within the limit.
        account.withdraw(30);
        System.out.println("After withdrawing 30: " + account.balance());

        // Now try to BREAK the invariant by overdrawing. The guard inside
        // withdraw() throws, so we catch it and prove the rule held.
        try {
            // Balance is 120; taking 500 must be refused.
            account.withdraw(500);
        } catch (IllegalArgumentException e) {
            // The account defended itself. This is what the naive public-field
            // version could NOT do.
            System.out.println("Blocked bad withdrawal: " + e.getMessage());
        }

        // ---- PART 2: INTERFACES -------------------------------------------
        System.out.println();
        System.out.println("== Interfaces: one loop, two very different devices ==");

        // Here is the load-bearing idea. This variable's TYPE is Switchable —
        // the contract — not Lamp or Fan. We are programming to the contract,
        // not to any one implementation.
        Switchable[] devices = { new Lamp(), new Fan() };

        // One single loop drives BOTH devices. It never asks "are you a lamp or
        // a fan?" It only knows they are Switchable, so it can call switchOn().
        // The right concrete behaviour is chosen at run time by each object.
        for (Switchable device : devices) {
            // Same call, different result per object — the lamp lights, the fan
            // spins. That "many behaviours behind one call" is POLYMORPHISM.
            device.switchOn();
            // Confirm each really flipped its own hidden state.
            System.out.println("  isOn? " + device.isOn());
        }
    }
}
