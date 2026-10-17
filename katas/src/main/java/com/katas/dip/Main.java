// The runnable demo for Lesson 09's kata, and its COMPOSITION ROOT.
package com.katas.dip;

// The ONE place allowed to know the concrete channels: here they are chosen
// and plugged into the policy.
import com.katas.dip.channels.EmailChannel;
import com.katas.dip.channels.SmsChannel;

// Builds the object graph by hand (manual dependency injection) and runs it.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // NAIVE: the policy builds its own email sender; nothing can be swapped.
        new com.katas.dip.naive.TemperatureMonitor().check(35);

        // INVERTED: choose a channel HERE and inject it into the policy.
        TemperatureMonitor byEmail = new TemperatureMonitor(new EmailChannel("ops@example.com"), 30);
        // 25C is fine: nothing is sent, nothing is printed.
        byEmail.check(25);
        // 35C is too hot: the alert goes out by email.
        byEmail.check(35);

        // The SAME policy class, wired to a different detail. Zero edits to it.
        TemperatureMonitor bySms = new TemperatureMonitor(new SmsChannel("555-0100"), 30);
        // The alert goes out by SMS.
        bySms.check(35);
    }
}
