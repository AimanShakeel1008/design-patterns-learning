// A second, different implementation of the same Switchable contract.
package com.katas.oopbasics;

// Same promise as Lamp, completely separate code. Neither class knows the
// other exists; they only share the contract.
public class Fan implements Switchable {

    // The fan's own private state; starts false, so every fan starts still.
    private boolean on;

    // Fulfils promise 1 for a fan.
    @Override
    public void turnOn() {
        // Remember that the fan is running.
        on = true;
    }

    // Fulfils promise 2 for a fan.
    @Override
    public void turnOff() {
        // Remember that the fan has stopped.
        on = false;
    }

    // Fulfils promise 3 for a fan.
    @Override
    public boolean isOn() {
        // Report the state; the field stays hidden.
        return on;
    }

    // Fulfils promise 4 in a FAN-specific way: same question, different answer.
    @Override
    public String describe() {
        // A fan spins or stands still.
        return "Fan is " + (on ? "spinning" : "still");
    }
}
