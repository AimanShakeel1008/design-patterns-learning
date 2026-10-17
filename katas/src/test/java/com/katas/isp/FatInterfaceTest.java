// Sits beside the role interfaces; reaches the naive classes by full name.
package com.katas.isp;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;

// PINS THE SYMPTOM on purpose: the fat interface forces the old printer to
// claim jobs it then refuses.
class FatInterfaceTest {

    // Print works; scan and fax are refused bequests.
    @Test
    void fatInterfaceForcesRefusedBequests() {
        // The old printer, seen through the fat Machine contract.
        com.katas.isp.naive.Machine old = new com.katas.isp.naive.OldPrinter();
        // Its real job works.
        assertEquals("Printed: Invoice", old.print("Invoice"));
        // The forced jobs throw.
        assertThrows(UnsupportedOperationException.class, () -> old.scan("Contract"));
        assertThrows(UnsupportedOperationException.class, () -> old.fax("Contract", "555-0100"));
    }
}
