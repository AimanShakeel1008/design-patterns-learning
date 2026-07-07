// The RIGHT-way version lives in the main kata package (no "naive" here).
package com.katas.oopbasics;

// RIGHT WAY. The same bank account, but now the balance is PROTECTED. The data
// and the rules that guard it live together in one object — that is what an
// "object" really is: data plus the behaviour that keeps the data sane.
public class BankAccount {

    // 'private' means ONLY code inside this class may touch this field. The
    // outside world cannot read or write it directly; it must go through the
    // methods below. This hiding of the field is called ENCAPSULATION.
    private double balance;

    // A constructor: the special method that builds a new BankAccount. It runs
    // once, when someone writes `new BankAccount(100)`, and its job is to leave
    // the new object in a valid starting state.
    public BankAccount(double openingBalance) {
        // Guard the RULE ("an account may never be negative") at the only door
        // through which a new account can be born. If the rule is broken we
        // refuse to build the object at all, by throwing an error.
        if (openingBalance < 0) {
            // IllegalArgumentException is Java's standard way to say "you passed
            // me a value I am not allowed to accept."
            throw new IllegalArgumentException("Opening balance cannot be negative.");
        }
        // 'this.balance' is the object's own field; 'openingBalance' is the
        // value handed in. We copy the accepted value into the guarded field.
        this.balance = openingBalance;
    }

    // Behaviour, not raw data access: adding money is an ACTION with its own rule.
    public void deposit(double amount) {
        // You cannot deposit a negative amount — that would be a sneaky withdrawal.
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative.");
        }
        // The rule held, so it is safe to change the protected field.
        this.balance += amount;
    }

    // Removing money is also an action, guarded by the account's core invariant.
    // An INVARIANT is a rule that must ALWAYS be true about an object — here,
    // "the balance is never negative."
    public void withdraw(double amount) {
        // Cannot withdraw a negative amount (that would secretly add money).
        if (amount < 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot be negative.");
        }
        // Cannot take out more than is there — this is the overdraft guard that
        // the naive public field could not enforce.
        if (amount > this.balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        // Both rules held, so the change is safe.
        this.balance -= amount;
    }

    // A read-only window onto the data. Callers can SEE the balance but cannot
    // SET it — they must go through deposit/withdraw, where the rules live.
    public double balance() {
        // Hand back a copy of the current value. double is a primitive, so this
        // is a plain copy; the caller cannot use it to reach back in and mutate us.
        return this.balance;
    }
}
