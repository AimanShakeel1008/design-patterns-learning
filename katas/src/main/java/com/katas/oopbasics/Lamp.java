package com.katas.oopbasics;

// A concrete class that "implements" the Switchable contract. 'implements
// Switchable' is a compiler-checked PROMISE: this class swears it provides every
// method the interface lists, or the code will not compile. This is the moment
// the abstract contract meets a real, working thing.
public class Lamp implements Switchable {

    // The lamp's own hidden state (encapsulation again): only the lamp decides
    // when this flips. The outside world learns it only through isOn().
    private boolean lit;

    // The lamp's PRIVATE way of turning on: it lights its bulb. Notice the fan
    // below will do something totally different for the same promise.
    @Override // Tells the compiler "this is meant to fulfil an interface method";
              // if the signature is wrong, we get an error instead of a silent miss.
    public void switchOn() {
        // Turning on, for a lamp, means the bulb becomes lit.
        this.lit = true;
        // A visible sign of the concrete behaviour, so the demo can be watched.
        System.out.println("Lamp: the bulb lights up.");
    }

    @Override
    public void switchOff() {
        // Turning off means the bulb goes dark.
        this.lit = false;
        System.out.println("Lamp: the bulb goes dark.");
    }

    @Override
    public boolean isOn() {
        // Report the lamp's current state truthfully.
        return this.lit;
    }
}
