// Test package mirrors the code package so the test can see BankAccount.
package com.katas.oopbasics;

// The @Test marker flags a method for JUnit to run.
import org.junit.jupiter.api.Test;
// Static imports of the assertion helpers we use, so we can write them plainly.
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

// Proves the encapsulated BankAccount enforces its rules. These assertions are
// what turn "I think it works" into "the machine confirms it works."
public class BankAccountTest {

    @Test
    void deposit_and_withdraw_update_the_balance() {
        // Start at 100.
        BankAccount account = new BankAccount(100);
        // Add 50 -> expect 150.
        account.deposit(50);
        // Take 30 -> expect 120.
        account.withdraw(30);
        // assertEquals(expected, actual, delta): doubles are compared within a
        // tiny tolerance (0.0001) to avoid floating-point surprises.
        assertEquals(120.0, account.balance(), 0.0001);
    }

    @Test
    void overdrawing_is_refused() {
        // Balance 100.
        BankAccount account = new BankAccount(100);
        // Withdrawing 500 must throw — the invariant "never negative" is defended.
        // assertThrows checks that the given code throws the given exception type.
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(500));
        // And after the refused withdrawal, the balance is untouched, still 100.
        assertEquals(100.0, account.balance(), 0.0001);
    }

    @Test
    void a_negative_opening_balance_is_refused() {
        // The constructor guards the rule at the door: no account may be born broken.
        assertThrows(IllegalArgumentException.class, () -> new BankAccount(-1));
    }
}
