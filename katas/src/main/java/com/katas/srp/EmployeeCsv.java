// The RIGHT WAY, part 4: the database team's storage format, and only theirs.
package com.katas.srp;

// Answers to ONE group: whoever owns storage. A new column or a different
// separator is a change here and nowhere else.
public class EmployeeCsv {

    // One comma-separated line: name, hours, rate.
    public String toCsv(EmployeeData employee) {
        // e.g. "Asha,45,20".
        return employee.getName() + "," + employee.getHoursWorked() + "," + employee.getHourlyRate();
    }
}
