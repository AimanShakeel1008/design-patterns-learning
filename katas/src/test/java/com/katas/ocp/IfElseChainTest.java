// The test sits beside the open/closed classes; it reaches the naive class by import.
package com.katas.ocp;

// The naive quoter with its if-else ladders.
import com.katas.ocp.naive.ShippingQuoter;
// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;

// PINS THE SYMPTOM on purpose: the naive quoter cannot accept a new method
// without being edited.
class IfElseChainTest {

    // Known names work; a new one is refused until both ladders are edited.
    @Test
    void naiveQuoterRefusesAnyMethodNotWrittenIntoItsLadders() {
        // The naive quoter.
        ShippingQuoter naive = new ShippingQuoter();
        // A name it knows.
        assertEquals("Express: 20 (2-day delivery)", naive.quote("Express", 5));
        // A name it was never taught.
        assertThrows(IllegalArgumentException.class, () -> naive.quote("Drone", 5));
    }
}
