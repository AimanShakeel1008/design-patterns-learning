// The runnable demo for Lesson 01's kata.
package com.katas.oopbasics;

// Shows three things in order: the naive account being broken from outside,
// the guarded account refusing the same abuse, and one loop driving two
// different classes through one interface.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // PART 1 - the wrong way. Two classes share the name BankAccount, so we
        // write the naive one's FULL name (package + class) to pick it exactly.
        com.katas.oopbasics.naive.BankAccount leaky = new com.katas.oopbasics.naive.BankAccount(100);
        // Because balance is public, outside code can overwrite it directly,
        // skipping withdraw() and its rule entirely.
        leaky.balance = -500;
        // Prove the damage: the account is now in an impossible state.
        System.out.println("Naive account balance: " + leaky.balance);

        // PART 2 - the right way. Same starting money, guarded account.
        BankAccount safe = new BankAccount(100);
        // The only way to change the balance is through a method with rules.
        safe.withdraw(30);
        // 100 - 30 leaves 70.
        System.out.println("Guarded account balance: " + safe.getBalance());
        // try: run code that might throw an exception, so we can handle it here.
        try {
            // Asking for more than is there: withdraw() will refuse by throwing.
            safe.withdraw(500);
        } catch (IllegalArgumentException e) {
            // catch: the thrown exception lands here; print its message.
            System.out.println("Refused: " + e.getMessage());
        }
        // The refused withdrawal changed nothing: the invariant held.
        System.out.println("Balance still: " + safe.getBalance());

        // PART 3 - programming to an interface. The array's type is the
        // CONTRACT (Switchable), so it can hold any class that keeps it.
        Switchable[] things = { new Lamp(), new Fan() };
        // One loop, written once, that never mentions Lamp or Fan.
        for (Switchable thing : things) {
            // The same call; each object runs its OWN turnOn code.
            thing.turnOn();
            // The same call again; each object answers in its own words.
            System.out.println(thing.describe());
        }
    }
}
