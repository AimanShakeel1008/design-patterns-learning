// Same package as the split classes.
package com.katas.srp;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves each class follows its OWN department's rule, independently.
class ResponsibilitiesTest {

    // The same 45-hour week at 20 per hour for every test.
    private final EmployeeData asha = new EmployeeData("Asha", 45, 20);

    // Accounting: 35 regular x 20 + 10 overtime x 40 = 700 + 400.
    @Test
    void payUsesAccountingsCutoff() {
        // Only PayCalculator knows the 35-hour rule.
        assertEquals(1100, new PayCalculator().calculatePay(asha));
    }

    // HR: a regular week is still 40 hours, whatever Accounting decides.
    @Test
    void hoursReportUsesHrsCutoff() {
        // Only HoursReporter knows the 40-hour rule.
        assertEquals("Asha: 40 regular hours", new HoursReporter().reportHours(asha));
    }

    // Storage: the plain comma-separated facts.
    @Test
    void csvHoldsTheRawFacts() {
        // Name, hours, rate.
        assertEquals("Asha,45,20", new EmployeeCsv().toCsv(asha));
    }
}
