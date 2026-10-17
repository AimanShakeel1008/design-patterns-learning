// STEP 5 (DIP): the delivery abstraction, owned by the security policy.
package com.katas.solid;

// In the policy's terms: "send this alert". Email and SMS live in the
// channels package and implement it; the policy never names them.
public interface AlertChannel {

    // Deliver one alert, however this channel does it.
    void send(String message);
}
