// The runnable demo for Lesson 05's kata.
package com.katas.srp;

// Runs one week for one employee through the god class and through the
// split classes, so the HR report's silent change is visible side by side.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // NAIVE: one class, three departments, one shared helper.
        com.katas.srp.naive.Employee asha = new com.katas.srp.naive.Employee("Asha", 45, 20);
        // Accounting's number is right: 35 x 20 + 10 x 40 = 1100.
        System.out.println("Naive pay: " + asha.calculatePay());
        // HR's number moved from 40 to 35 although HR asked for nothing.
        System.out.println("Naive HR report: " + asha.reportHours());

        // SRP: the same facts, three classes, each answering to one department.
        EmployeeData data = new EmployeeData("Asha", 45, 20);
        // Accounting's rule, kept in PayCalculator.
        System.out.println("SRP pay: " + new PayCalculator().calculatePay(data));
        // HR's rule, kept in HoursReporter: still 40.
        System.out.println("SRP HR report: " + new HoursReporter().reportHours(data));
        // Storage format, kept in EmployeeCsv.
        System.out.println("SRP CSV: " + new EmployeeCsv().toCsv(data));
    }
}
