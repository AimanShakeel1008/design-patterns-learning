// Tests live in the same package as the class they check.
package com.katas.oopbasics;

// The @Test marker that tells JUnit to run a method as a test.
import org.junit.jupiter.api.Test;

// assertEquals(expected, actual): fails the test if the two differ.
import static org.junit.jupiter.api.Assertions.assertEquals;
// assertThrows(type, code): fails the test UNLESS running the code throws
// an exception of that type.
import static org.junit.jupiter.api.Assertions.assertThrows;

// Proves the guarded BankAccount keeps its invariant: balance never negative.
class BankAccountTest {

    // The normal path: a valid withdrawal reduces the balance.
    @Test
    void withdrawReducesBalance() {
        // Start with 100.
        BankAccount account = new BankAccount(100);
        // Take 30 out through the guarded method.
        account.withdraw(30);
        // 100 - 30 = 70.
        assertEquals(70, account.getBalance());
    }

    // The other normal path: a valid deposit increases the balance.
    @Test
    void depositIncreasesBalance() {
        // Start with 100.
        BankAccount account = new BankAccount(100);
        // Put 50 in.
        account.deposit(50);
        // 100 + 50 = 150.
        assertEquals(150, account.getBalance());
    }

    // The guard that matters most: overdrawing is refused AND changes nothing.
    @Test
    void overdrawIsRefusedAndBalanceIsUnchanged() {
        // Start with 100.
        BankAccount account = new BankAccount(100);
        // () -> account.withdraw(500) is a small bundle of code handed to
        // assertThrows, which runs it and checks that it throws.
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(500));
        // The refused request must leave the balance exactly as it was.
        assertEquals(100, account.getBalance());
    }

    // The constructor guard: a broken account can never be created.
    @Test
    void negativeOpeningBalanceIsRefused() {
        // Building an account with -1 must throw, so no object is ever made.
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }

    // Nonsense amounts are refused instead of silently doing something odd.
    @Test
    void nonPositiveAmountsAreRefused() {
        // Start with 100.
        BankAccount account = new BankAccount(100);
        // A zero deposit must be refused.
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0));
        // A negative withdrawal (which would ADD money) must be refused.
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-20));
        // Neither refused request changed anything.
        assertEquals(100, account.getBalance());
    }
}
