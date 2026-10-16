// The RIGHT WAY, part 1: the employee's facts, with no department's rules.
package com.katas.srp;

// Just the data every department needs. It has nothing to change for except
// "what we record about an employee", so it answers to no single department.
public class EmployeeData {

    // The employee's name.
    private final String name;

    // Hours worked this week.
    private final int hoursWorked;

    // Pay per hour, in whole currency units.
    private final int hourlyRate;

    // Build the record.
    public EmployeeData(String name, int hoursWorked, int hourlyRate) {
        // Store the name.
        this.name = name;
        // Store the hours.
        this.hoursWorked = hoursWorked;
        // Store the rate.
        this.hourlyRate = hourlyRate;
    }

    // Read the name.
    public String getName() {
        // Hand back the field.
        return name;
    }

    // Read the hours.
    public int getHoursWorked() {
        // Hand back the field.
        return hoursWorked;
    }

    // Read the rate.
    public int getHourlyRate() {
        // Hand back the field.
        return hourlyRate;
    }
}
