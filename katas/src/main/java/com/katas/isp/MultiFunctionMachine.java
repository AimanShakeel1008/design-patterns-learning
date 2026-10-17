// A machine that really does all three jobs.
package com.katas.isp;

// One class may implement SEVERAL small interfaces. It can be handed to a
// print-only client as a Printer, to a scan-only client as a Scanner, and so
// on; each client sees only the role it needs.
public class MultiFunctionMachine implements Printer, Scanner, Fax {

    // Keeps the Printer role.
    @Override
    public String print(String document) {
        // Report the print.
        return "Printed: " + document;
    }

    // Keeps the Scanner role.
    @Override
    public String scan(String document) {
        // Report the scan.
        return "Scanned: " + document;
    }

    // Keeps the Fax role.
    @Override
    public String fax(String document, String number) {
        // Report the fax.
        return "Faxed " + document + " to " + number;
    }
}
