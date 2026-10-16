// WRONG-WAY VERSION, kept on purpose so the lesson can show what breaks.
// It lives in its own "naive" package so it never gets mixed up with the
// guarded version in com.katas.oopbasics.
package com.katas.oopbasics.naive;

// A bank account whose data is NOT protected. Compare it line by line with
// com.katas.oopbasics.BankAccount to see what encapsulation adds.
public class BankAccount {

    // THE FLAW: the field is public, so ANY code anywhere can read it AND
    // overwrite it directly, skipping every rule withdraw() tries to enforce.
    public int balance;

    // The constructor stores the opening balance. It never checks for a
    // negative number, so an account can even be BORN broken.
    public BankAccount(int openingBalance) {
        // Copy the argument into this object's field.
        this.balance = openingBalance;
    }

    // A withdraw method that does contain a rule ("never take more than is
    // there"). But the rule only protects callers who CHOOSE to use it.
    public void withdraw(int amount) {
        // Only take the money if there is enough of it...
        if (amount <= balance) {
            // ...and subtract it from the balance.
            balance = balance - amount;
        }
        // Otherwise it silently does nothing: the caller never learns that
        // the request was refused. That is a second, quieter flaw.
    }
}
