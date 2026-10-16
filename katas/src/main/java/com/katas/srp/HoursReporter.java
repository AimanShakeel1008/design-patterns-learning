// The RIGHT WAY, part 3: HR's rules, and only HR's.
package com.katas.srp;

// Answers to ONE group: HR. Its idea of "regular hours" is its own, and it
// stays 40 no matter what Accounting decides about pay.
public class HoursReporter {

    // HR's rule: a regular working week is up to 40 hours.
    private static final int REGULAR_WEEK = 40;

    // One line for HR's weekly report.
    public String reportHours(EmployeeData employee) {
        // Hours up to HR's cutoff.
        int regular = Math.min(employee.getHoursWorked(), REGULAR_WEEK);
        // e.g. "Asha: 40 regular hours".
        return employee.getName() + ": " + regular + " regular hours";
    }
}
