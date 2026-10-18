// Same package as the KISS kata.
package com.katas.kiss;

// The over-built version, kept frozen to show the pain.
import com.katas.kiss.naive.GreetingEngine;
// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Pins the naive engine's bug: hidden ordering swallowed the first extension.
class EngineShadowingTest {

    // A rule added later is never reached for hours an older rule already claims.
    @Test
    void aNewRuleAddedLaterIsHiddenByAnOlderOne() {
        // The engine with its three original rules.
        GreetingEngine engine = new GreetingEngine();
        // The extension for "Good night from 22:00".
        engine.addRule(new GreetingEngine.NightRule());
        // EveningRule (hour >= 18) is asked first and answers for 23.
        assertEquals("Good evening", engine.greet(23));
    }
}
