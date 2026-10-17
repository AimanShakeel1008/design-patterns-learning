// The runnable demo for Lesson 08's kata.
package com.katas.isp;

// List.of for the documents.
import java.util.List;

// Shows the fat interface forcing refusals, then small role interfaces that
// let each machine promise only what it does and each client ask only for
// what it uses.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // NAIVE: the old printer must claim to be a full Machine.
        com.katas.isp.naive.Machine old = new com.katas.isp.naive.OldPrinter();
        // Printing works...
        System.out.println("Naive old printer: " + old.print("Invoice"));
        // ...but the fat contract also lets a caller ask it to scan.
        try {
            // A promise the old printer cannot keep.
            System.out.println(old.scan("Contract"));
        } catch (UnsupportedOperationException e) {
            // The refused bequest, caught.
            System.out.println("Naive old printer refused: " + e.getMessage());
        }

        // SEGREGATED: a print-only client served by the old printer.
        ReportPrinter reports = new ReportPrinter(new OldPrinter());
        // Each document printed, each result line shown.
        for (String line : reports.printAll(List.of("Invoice", "Receipt"))) {
            // One line per document.
            System.out.println(line);
        }

        // One multi-function machine, seen through two different roles.
        MultiFunctionMachine office = new MultiFunctionMachine();
        // As a Scanner: this variable can only scan.
        Scanner scanner = office;
        // As a Fax: this variable can only fax.
        Fax fax = office;
        // Use each role.
        System.out.println(scanner.scan("Contract"));
        // The same object, a different role.
        System.out.println(fax.fax("Contract", "555-0100"));
    }
}
