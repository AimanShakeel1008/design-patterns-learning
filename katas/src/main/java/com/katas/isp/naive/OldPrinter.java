// WRONG WAY: a simple printer forced to "implement" jobs it cannot do.
package com.katas.isp.naive;

// An old printer only prints. But Machine demands scan() and fax() too, so
// the only options are to refuse them (a refused bequest that breaks the
// Machine contract, Lesson 07) or to pretend (worse). We refuse.
public class OldPrinter implements Machine {

    // The one job this machine can really do.
    @Override
    public String print(String document) {
        // Report the print.
        return "Printed: " + document;
    }

    // FORCED by the fat interface, and impossible to honour.
    @Override
    public String scan(String document) {
        // A refused bequest: any client trusting Machine.scan will crash here.
        throw new UnsupportedOperationException("Old printers cannot scan");
    }

    // FORCED by the fat interface, and impossible to honour.
    @Override
    public String fax(String document, String number) {
        // Another refused bequest.
        throw new UnsupportedOperationException("Old printers cannot fax");
    }
}
