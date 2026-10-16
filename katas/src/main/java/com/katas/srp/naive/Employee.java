// WRONG-WAY VERSION, kept on purpose: one class answering to three departments.
package com.katas.srp.naive;

// An employee record that also calculates pay (for Accounting), reports
// hours (for HR) and formats itself for storage (for the database team).
// Three groups of people can ask for changes to this one class. Worse,
// Accounting's method and HR's method SHARE a private helper.
public class Employee {

    // The employee's name.
    private final String name;

    // Hours worked this week.
    private final int hoursWorked;

    // Pay per hour, in whole currency units.
    private final int hourlyRate;

    // Build an employee record.
    public Employee(String name, int hoursWorked, int hourlyRate) {
        // Store the name.
        this.name = name;
        // Store the hours.
        this.hoursWorked = hoursWorked;
        // Store the rate.
        this.hourlyRate = hourlyRate;
    }

    // FOR ACCOUNTING: regular hours at the normal rate, overtime at double.
    public int calculatePay() {
        // Uses the shared helper below.
        return regularHours() * hourlyRate + overtimeHours() * hourlyRate * 2;
    }

    // FOR HR: how many regular hours were worked this week.
    public String reportHours() {
        // ALSO uses the shared helper below, so it silently follows any
        // change Accounting asks for.
        return name + ": " + regularHours() + " regular hours";
    }

    // FOR THE DATABASE TEAM: one line of comma-separated values for storage.
    public String toCsv() {
        // Name, hours, rate, separated by commas.
        return name + "," + hoursWorked + "," + hourlyRate;
    }

    // THE SHARED HELPER. Accounting asked: "for PAY, overtime starts after
    // 35 hours." A developer changed 40 to 35 here. Pay is now right, but
    // reportHours() changed too, and HR never asked for that.
    private int regularHours() {
        // Hours up to the cutoff count as regular.
        return Math.min(hoursWorked, 35);
    }

    // Hours beyond the cutoff.
    private int overtimeHours() {
        // Everything above the regular hours, never negative.
        return Math.max(hoursWorked - regularHours(), 0);
    }
}
