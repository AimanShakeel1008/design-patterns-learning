// The security POLICY package owns its delivery abstraction (DIP).
package com.smarthome.security;

// In the policy's terms: "send this alert". Delivery details (the console
// today, a phone app tomorrow) live in com.smarthome.alerts and implement it.
public interface AlertChannel {

    // Deliver one alert, however this channel does it.
    void send(String message);
}
