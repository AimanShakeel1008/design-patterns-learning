// Test package mirrors the main package so tests can see the hub's classes.
package com.smarthome;

// The @Test marker that flags a method for JUnit to run.
import org.junit.jupiter.api.Test;
// assertTrue(condition) fails the test if the condition is false.
import static org.junit.jupiter.api.Assertions.assertTrue;

// The hub's smoke test: confirms the Smart Home build-and-test setup works,
// before any real hub behaviour exists to test.
public class SmokeTest {

    // One trivial passing test. If Maven + JUnit + the compiler are healthy,
    // this runs and passes; if the toolchain is broken, it never gets here.
    @Test
    void toolchain_is_wired_up() {
        // Assert a condition that is always true. Passing means the machinery
        // (not the logic) is proven to work end to end.
        assertTrue(true);
    }
}
