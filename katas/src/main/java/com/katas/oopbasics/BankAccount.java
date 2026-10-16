// The RIGHT-WAY version: the same idea as naive.BankAccount, but encapsulated.
package com.katas.oopbasics;

// A bank account that guards its own data. Its invariant (the rule that must
// ALWAYS hold) is: the balance is never negative.
public class BankAccount {

    // private: only code inside THIS class can touch the balance. Every other
    // class must go through the public methods below, which enforce the rule.
    private int balance;

    // The constructor is the first guard: it refuses to build an account that
    // already breaks the invariant, so no broken account can ever exist.
    public BankAccount(int openingBalance) {
        // Reject a negative opening balance before storing anything.
        if (openingBalance < 0) {
            // throw stops the method right here and reports the problem to the
            // caller; the half-built object is thrown away and never used.
            throw new IllegalArgumentException("Opening balance cannot be negative: " + openingBalance);
        }
        // Safe to store: the invariant holds from the very first moment.
        this.balance = openingBalance;
    }

    // Add money. The only way the balance can grow.
    public void deposit(int amount) {
        // A deposit of zero or less makes no sense, so refuse it loudly.
        if (amount <= 0) {
            // Report the bad request instead of silently ignoring it.
            throw new IllegalArgumentException("Deposit must be positive: " + amount);
        }
        // The amount is valid, so apply it.
        balance = balance + amount;
    }

    // Take money out. The only way the balance can shrink.
    public void withdraw(int amount) {
        // A withdrawal of zero or less makes no sense either.
        if (amount <= 0) {
            // Refuse it, saying exactly what was wrong.
            throw new IllegalArgumentException("Withdrawal must be positive: " + amount);
        }
        // The rule that protects the invariant: never take more than is there.
        if (amount > balance) {
            // Refuse, and tell the caller both numbers so the cause is obvious.
            throw new IllegalArgumentException(
                    "Insufficient funds: balance " + balance + ", requested " + amount);
        }
        // Both checks passed, so the balance stays at zero or above.
        balance = balance - amount;
    }

    // Let other code READ the balance without being able to WRITE it.
    public int getBalance() {
        // Hand back a copy of the number; the field itself stays private.
        return balance;
    }
}
