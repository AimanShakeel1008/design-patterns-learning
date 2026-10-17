// The RIGHT WAY, part 1: the abstraction, OWNED by the policy side.
package com.katas.dip;

// Written in the POLICY's terms ("send this alert"), not in any channel's
// terms (no email subjects, no phone numbers). It lives in the policy's
// package; the low-level channels come to IT and implement it. That reversal
// of who depends on whom is the "inversion".
public interface AlertChannel {

    // Deliver one alert message, however this channel does it.
    void send(String message);
}
