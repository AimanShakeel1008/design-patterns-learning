// Same package as the role interfaces.
package com.katas.isp;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for inputs and expected values.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves each machine and client deals only in the roles it really has.
class SegregatedRolesTest {

    // An old printer fully serves a client that only needs printing.
    @Test
    void oldPrinterServesAPrintOnlyClient() {
        // The print-only client, with the print-only machine.
        ReportPrinter reports = new ReportPrinter(new OldPrinter());
        // Both documents printed.
        assertEquals(List.of("Printed: Invoice", "Printed: Receipt"), reports.printAll(List.of("Invoice", "Receipt")));
    }

    // One machine can play several roles, each through its own small interface.
    @Test
    void multiFunctionMachineServesEveryRole() {
        // One object...
        MultiFunctionMachine office = new MultiFunctionMachine();
        // ...used as a Printer...
        Printer printer = office;
        assertEquals("Printed: Memo", printer.print("Memo"));
        // ...as a Scanner...
        Scanner scanner = office;
        assertEquals("Scanned: Memo", scanner.scan("Memo"));
        // ...and as a Fax.
        Fax fax = office;
        assertEquals("Faxed Memo to 555-0100", fax.fax("Memo", "555-0100"));
    }

    // The payoff for tests: a one-method fake is all the client needs.
    @Test
    void aOneMethodFakeIsEnoughForAPrintOnlyClient() {
        // The tiny fake.
        RecordingPrinter fake = new RecordingPrinter();
        // The client cannot tell it apart from a real printer.
        new ReportPrinter(fake).printAll(List.of("A", "B"));
        // The fake saw both documents, in order.
        assertEquals(List.of("A", "B"), fake.received());
    }
}
