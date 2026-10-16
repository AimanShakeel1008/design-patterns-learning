// The RIGHT WAY, part 2: Accounting's rules, and only Accounting's.
package com.katas.srp;

// Answers to ONE group: Accounting. Its overtime cutoff is private to it,
// so changing it can never touch HR's report.
public class PayCalculator {

    // Accounting's rule: for pay, overtime starts after 35 hours.
    private static final int PAY_OVERTIME_AFTER = 35;

    // Regular hours at the normal rate, overtime at double.
    public int calculatePay(EmployeeData employee) {
        // Hours up to Accounting's cutoff.
        int regular = Math.min(employee.getHoursWorked(), PAY_OVERTIME_AFTER);
        // Hours beyond it, never negative.
        int overtime = Math.max(employee.getHoursWorked() - regular, 0);
        // Normal pay plus double-rate overtime.
        return regular * employee.getHourlyRate() + overtime * employee.getHourlyRate() * 2;
    }
}
