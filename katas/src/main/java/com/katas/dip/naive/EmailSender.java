// WRONG-WAY setup, kept on purpose: a low-level detail.
package com.katas.dip.naive;

// The email "driver": the nuts and bolts of sending one message. (It prints
// instead of really emailing, so the demo stays predictable.)
public class EmailSender {

    // Send one email; its parameters are shaped by HOW email works.
    public void sendEmail(String to, String subject, String body) {
        // Show what would be sent.
        System.out.println("EMAIL to " + to + " [" + subject + "]: " + body);
    }
}
