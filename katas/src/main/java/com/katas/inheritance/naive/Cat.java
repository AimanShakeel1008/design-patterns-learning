// WRONG-WAY VERSION, kept on purpose: the copy of naive.Dog.
package com.katas.inheritance.naive;

// Line for line the same as naive.Dog, except for the sound.
public class Cat {

    // COPY of Dog's field.
    private final String name;

    // COPY of Dog's constructor.
    public Cat(String name) {
        // Store the name, exactly as Dog does.
        this.name = name;
    }

    // COPY of Dog's getter.
    public String getName() {
        // Return the name, exactly as Dog does.
        return name;
    }

    // COPY of Dog's introduce(), with "Meow" in place of "Woof". A fix made in
    // Dog but forgotten here would make the two quietly disagree.
    public String introduce() {
        // The one line that genuinely differs.
        return "I am " + name + " and I say Meow";
    }
}
