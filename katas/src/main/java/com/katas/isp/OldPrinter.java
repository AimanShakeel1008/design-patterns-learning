// An old printer, now promising only what it can do.
package com.katas.isp;

// Implements ONE small role. There is nothing to refuse, so it keeps its
// whole contract (Lesson 07) without a single exception.
public class OldPrinter implements Printer {

    // Its one real job.
    @Override
    public String print(String document) {
        // Report the print.
        return "Printed: " + document;
    }
}
