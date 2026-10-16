// Demonstrates OVERLOADING, and the classic trap that comes with it.
package com.katas.inheritance;

// A vet with two methods of the SAME name but DIFFERENT parameter types.
// Which one runs is decided by the COMPILER, from the declared type of the
// argument, not by the JVM from the actual object.
public class Vet {

    // Version 1: accepts any Animal.
    public String examine(Animal animal) {
        // The general check-up.
        return "General check-up for " + animal.getName();
    }

    // Version 2: same name, but a more specific parameter type (Dog).
    public String examine(Dog dog) {
        // The dog-specific check-up.
        return "Dog check-up for " + dog.getName();
    }
}
