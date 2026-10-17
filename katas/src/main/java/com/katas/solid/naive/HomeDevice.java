// WRONG-WAY VERSION, kept on purpose: the rotten module's FAT interface.
package com.katas.solid.naive;

// One contract for every device in the house, whatever it can really do.
// A lock must "detect smoke", a smoke alarm must "be locked", and both must
// "turn on". (ISP violation, which forces LSP violations in the classes.)
public interface HomeDevice {

    // The device's name.
    String name();

    // A text tag the security code switches on: "lock" or "smoke". (It feeds
    // an if-else ladder: an OCP violation waiting to grow.)
    String type();

    // Meaningful only for locks.
    boolean isLocked();

    // Meaningful only for smoke alarms.
    boolean smokeDetected();

    // Meaningful for neither a lock nor an alarm.
    void turnOn();

    // Meaningful for neither a lock nor an alarm.
    void turnOff();
}
