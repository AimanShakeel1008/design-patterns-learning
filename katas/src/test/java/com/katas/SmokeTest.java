// Same package as the code under test. Test sources live in src/test/java,
// but share the package name so tests can reach package-visible members.
package com.katas;

// Import the @Test annotation: the marker that tells JUnit "run this method
// as a test". An annotation is a tag attached to code that tools can read.
import org.junit.jupiter.api.Test;
// Import the assertion helper. assertEquals(expected, actual) fails the test
// if the two values are not equal. A "static import" lets us write the method
// name directly (assertEquals) instead of Assertions.assertEquals.
import static org.junit.jupiter.api.Assertions.assertEquals;

// A "smoke test" is the simplest possible test: it does not check real logic,
// it just confirms the build-and-test machinery itself is wired up correctly.
// (The name comes from switching on a device and checking no smoke comes out.)
public class SmokeTest {

    // @Test marks this method as one JUnit should execute. No return value; a
    // test passes if it finishes without an assertion throwing an error.
    @Test
    void toolchain_is_wired_up() {
        // Assert that 2 + 2 equals 4. This is trivially true, so the test
        // passes ONLY if Maven, JUnit, and the compiler are all working.
        assertEquals(4, 2 + 2);
    }
}
