// The RIGHT WAY, part 3: the faxing role on its own.
package com.katas.isp;

// Only machines that can really fax sign this contract.
public interface Fax {

    // Fax a document to a number; returns a line saying what happened.
    String fax(String document, String number);
}
