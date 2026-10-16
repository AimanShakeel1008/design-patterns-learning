// The contract every switchable thing in the kata promises to fulfil.
package com.katas.oopbasics;

// An interface lists WHAT a thing can do, never HOW. Any class that says
// "implements Switchable" must provide real code for all four methods,
// and the compiler checks that promise.
public interface Switchable {

    // Promise 1: it can be switched on. No body here: each class decides how.
    void turnOn();

    // Promise 2: it can be switched off.
    void turnOff();

    // Promise 3: it can say whether it is currently on.
    boolean isOn();

    // Promise 4: it can describe itself in one line of text.
    String describe();
}
