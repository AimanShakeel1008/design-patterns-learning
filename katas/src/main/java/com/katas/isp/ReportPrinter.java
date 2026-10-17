// A CLIENT that only prints, so it asks only for the printing role.
package com.katas.isp;

// The JDK's growable list.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// Depends on Printer alone: changes to scanning or faxing can never affect
// it, any printer (old or multi-function) can serve it, and its test needs a
// one-method stand-in.
public class ReportPrinter {

    // The only capability this client needs.
    private final Printer printer;

    // The caller decides which printer.
    public ReportPrinter(Printer printer) {
        // Remember it.
        this.printer = printer;
    }

    // Print every document, collecting what the printer reports.
    public List<String> printAll(List<String> documents) {
        // One result line per document.
        List<String> results = new ArrayList<>();
        // Print each in turn.
        for (String document : documents) {
            // The single method this client uses.
            results.add(printer.print(document));
        }
        // An unmodifiable copy for the caller.
        return List.copyOf(results);
    }
}
