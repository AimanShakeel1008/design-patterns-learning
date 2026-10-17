// The RIGHT WAY, part 2: the scanning role on its own.
package com.katas.isp;

// Only machines that can really scan sign this contract.
// (Named after the role; it is unrelated to the JDK's java.util.Scanner.)
public interface Scanner {

    // Scan a document; returns a line saying what happened.
    String scan(String document);
}
