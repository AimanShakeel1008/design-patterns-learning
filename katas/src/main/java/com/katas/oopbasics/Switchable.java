package com.katas.oopbasics;

// An INTERFACE is a pure CONTRACT: a list of things a class promises it can do,
// with NO code for how it does them. It names capabilities, not machinery.
// Anything that "is Switchable" swears it can be switched on, switched off, and
// asked whether it is currently on. HOW a lamp switches on and HOW a fan
// switches on are completely different — but the CONTRACT is identical, and
// that shared contract is what lets other code treat them the same.
public interface Switchable {

    // A method with no body — just a signature ending in a semicolon. This is a
    // PROMISE: "every Switchable will provide a switchOn() method." The interface
    // says WHAT must exist; each implementing class supplies the WHAT-actually-happens.
    void switchOn();

    // The matching promise to turn the thing off.
    void switchOff();

    // The promise to report current state. Returns true if on, false if off.
    boolean isOn();
}
