// WRONG-WAY VERSION, kept on purpose: one fat interface for every office machine.
package com.katas.isp.naive;

// A FAT interface: it bundles three different jobs into one contract. Every
// machine must promise all three, and every client that needs just one job
// still depends on all three.
public interface Machine {

    // Print a document; returns a line saying what happened.
    String print(String document);

    // Scan a document; returns a line saying what happened.
    String scan(String document);

    // Fax a document to a number; returns a line saying what happened.
    String fax(String document, String number);
}
