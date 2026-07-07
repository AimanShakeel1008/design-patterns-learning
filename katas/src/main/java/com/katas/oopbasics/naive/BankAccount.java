// This kata's package. The folders on disk (com/katas/oopbasics/naive) must
// mirror this line exactly. The "naive" sub-package marks the WRONG-way code,
// kept on purpose so we can compare it against the fixed version next door.
package com.katas.oopbasics.naive;

// WRONG WAY. A bank account with NO encapsulation: its most important piece of
// data — the balance — is left wide open for any code anywhere to change.
public class BankAccount {

    // 'public' means any class in the whole program may read AND write this
    // field directly, with zero checks. Nothing stops another line of code from
    // writing `account.balance = -1000000;` and putting the account into an
    // impossible state. The data has no guard standing in front of it.
    public double balance;
}
