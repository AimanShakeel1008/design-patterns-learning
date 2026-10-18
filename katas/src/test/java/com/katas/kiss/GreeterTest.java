// Same package as the simple greeter.
package com.katas.kiss;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// The simple version is easy to test completely: every boundary, one test.
class GreeterTest {

    // Each part of the day, checked at both of its edges.
    @Test
    void eachPartOfTheDayHasItsGreeting() {
        // The object under test.
        Greeter greeter = new Greeter();
        // Morning: midnight up to 11.
        assertEquals("Good morning", greeter.greet(0));
        assertEquals("Good morning", greeter.greet(11));
        // Afternoon: 12 up to 17.
        assertEquals("Good afternoon", greeter.greet(12));
        assertEquals("Good afternoon", greeter.greet(17));
        // Evening: 18 up to 21.
        assertEquals("Good evening", greeter.greet(18));
        assertEquals("Good evening", greeter.greet(21));
        // Night: 22 and 23.
        assertEquals("Good night", greeter.greet(22));
        assertEquals("Good night", greeter.greet(23));
    }
}
