// The RIGHT WAY, part 1: one small interface per ROLE a client needs.
package com.katas.isp;

// The printing role, and nothing else. A client that only prints depends on
// exactly this one method.
public interface Printer {

    // Print a document; returns a line saying what happened.
    String print(String document);
}
