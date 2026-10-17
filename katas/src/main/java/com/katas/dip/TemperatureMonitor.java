// The RIGHT WAY, part 2: the high-level policy, depending only on its abstraction.
package com.katas.dip;

// The same rule as the naive version, but it never names or creates a
// channel. The channel is INJECTED: handed in through the constructor by
// whoever assembles the program. Notice this file imports nothing from the
// channels package.
public class TemperatureMonitor {

    // The abstraction the policy needs; which channel it is, is not its business.
    private final AlertChannel channel;

    // The highest temperature that is still fine.
    private final int maxCelsius;

    // CONSTRUCTOR INJECTION: every dependency arrives as a parameter.
    public TemperatureMonitor(AlertChannel channel, int maxCelsius) {
        // Remember the channel we were given.
        this.channel = channel;
        // Remember the limit we were given.
        this.maxCelsius = maxCelsius;
    }

    // The policy: above the limit, raise the alarm. Returns whether it alerted.
    public boolean check(int celsius) {
        // Within the limit: nothing to do.
        if (celsius <= maxCelsius) {
            // No alert.
            return false;
        }
        // Too hot: send the alert in the policy's own terms.
        channel.send("Server room at " + celsius + "C (max " + maxCelsius + "C)");
        // An alert went out.
        return true;
    }
}
