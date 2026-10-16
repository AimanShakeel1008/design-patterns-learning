// The test sits beside the SRP classes; it reaches the naive class by import.
package com.katas.srp;

// The god class from the naive package.
import com.katas.srp.naive.Employee;
// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// PINS THE SYMPTOM on purpose: Accounting's change leaked into HR's report.
// This test passes while the bug is present, so the lesson's claim is checked
// by the machine.
class GodClassTest {

    // Pay is right, but HR's report moved from 40 to 35 regular hours.
    @Test
    void accountingsChangeLeaksIntoHrsReport() {
        // The same 45-hour week at 20 per hour.
        Employee asha = new Employee("Asha", 45, 20);
        // Accounting got what it asked for...
        assertEquals(1100, asha.calculatePay());
        // ...and HR got a change it never asked for: 35, not 40.
        assertEquals("Asha: 35 regular hours", asha.reportHours());
    }
}
