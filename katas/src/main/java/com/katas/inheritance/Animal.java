// The RIGHT-WAY version: the shared parts written once, in a parent class.
package com.katas.inheritance;

// abstract: Animal is an incomplete class. It holds everything every animal
// shares, but it cannot be built on its own (new Animal(...) will not
// compile), because it does not know what sound to make.
public abstract class Animal {

    // Written ONCE here; every subclass object carries its own copy of it.
    // private: even subclasses must use getName() to read it.
    private final String name;

    // protected: callable from subclasses (and this package), not from the
    // outside world. Only a subclass constructor, via super(name), uses it.
    protected Animal(String name) {
        // Store the name for whichever animal is being built.
        this.name = name;
    }

    // Shared behaviour: every animal reports its name the same way.
    public String getName() {
        // Hand back the private name.
        return name;
    }

    // Shared behaviour that DEPENDS on a part each subclass supplies. When it
    // calls speak(), the JVM runs the speak() of the ACTUAL object (a Dog,
    // a Cat, a Puppy), even though this code lives in Animal.
    public String introduce() {
        // One introduction, written once, for every kind of animal.
        return "I am " + name + " and I say " + speak();
    }

    // abstract method: a promise with no body. Every concrete subclass MUST
    // override it, or that subclass will not compile.
    public abstract String speak();

    // Every Java class silently extends Object, which has a toString() that
    // println uses. We override it so printing an animal shows something
    // readable instead of the default "ClassName@hexnumber".
    @Override
    public String toString() {
        // getClass().getSimpleName() is the ACTUAL class's short name, so a
        // Puppy prints as "Puppy Bit" even though this code lives in Animal.
        return getClass().getSimpleName() + " " + name;
    }
}
