// A third level: Puppy extends Dog, which extends Animal.
package com.katas.inheritance;

// A Puppy IS a Dog (and therefore also an Animal). It exists to show the
// JVM's method lookup walking up more than one level, and super.speak().
public class Puppy extends Dog {

    // Build the Dog part (which in turn builds the Animal part).
    public Puppy(String name) {
        // Calls Dog's constructor, which calls Animal's.
        super(name);
    }

    // Overrides Dog's speak(). A Puppy's speak() replaces Dog's for Puppy
    // objects, but can still reach the parent version with super.speak().
    @Override
    public String speak() {
        // super.speak() runs DOG's speak(), giving "Woof".
        return "Yip (learning to " + super.speak() + ")";
    }
}
