// WRONG WAY: high-level policy welded to a low-level detail.
package com.katas.dip.naive;

// The POLICY ("alert someone when the server room is too hot") is the
// valuable, stable idea. But this class creates and names the email detail
// itself, so: switching to SMS means editing the policy; testing the policy
// means really "sending" email; and the policy can never be reused without
// dragging EmailSender along. The dependency points from policy DOWN to detail.
public class TemperatureMonitor {

    // DEPENDS ON A DETAIL: the concrete class, created right here with new.
    private final EmailSender email = new EmailSender();

    // The policy: above 30C, raise the alarm.
    public void check(int celsius) {
        // The rule itself...
        if (celsius > 30) {
            // ...expressed in the detail's own terms (to, subject, body).
            email.sendEmail("ops@example.com", "Too hot", "Server room at " + celsius + "C");
        }
    }
}
