// Same package as the YAGNI kata.
package com.katas.yagni;

// The over-prepared version, kept frozen to show the pain.
import com.katas.yagni.naive.ListPrinter;
// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for the items.
import java.util.List;

// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;

// Pins the trap: a speculative feature that compiles, ships, and fails when finally used.
class SpeculativePrinterTest {

    // Asking for two columns is accepted by the constructor and refused only at print time.
    @Test
    void anUnfinishedFutureFeatureFailsOnlyWhenSomeoneTriesIt() {
        // Building it is fine: nothing warns the caller.
        ListPrinter printer = new ListPrinter("plain", false, 2);
        // Using it throws.
        assertThrows(UnsupportedOperationException.class, () -> printer.print(List.of("milk")));
    }
}
