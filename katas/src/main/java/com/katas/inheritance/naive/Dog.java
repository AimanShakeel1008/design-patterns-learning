// WRONG-WAY VERSION, kept on purpose: two classes that copy each other.
package com.katas.inheritance.naive;

// A dog written with NO sharing. Compare it with naive.Cat: every line except
// the sound is a copy. That duplication is the pain this lesson cures.
public class Dog {

    // DUPLICATED in Cat: the same field, with the same meaning.
    private final String name;

    // DUPLICATED in Cat: the same constructor body.
    public Dog(String name) {
        // Store the name, exactly as Cat does.
        this.name = name;
    }

    // DUPLICATED in Cat: the same getter.
    public String getName() {
        // Return the name, exactly as Cat does.
        return name;
    }

    // DUPLICATED in Cat except for one word. If the introduction must change
    // (say, to add an age), BOTH copies must be found and edited identically.
    public String introduce() {
        // The only real difference between Dog and Cat is "Woof".
        return "I am " + name + " and I say Woof";
    }
}
