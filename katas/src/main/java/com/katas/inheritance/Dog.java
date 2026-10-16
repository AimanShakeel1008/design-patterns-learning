// A concrete animal: it fills in the one missing piece.
package com.katas.inheritance;

// "extends Animal": a Dog IS an Animal. It inherits the name field,
// getName(), introduce() and toString(), and supplies only what differs.
public class Dog extends Animal {

    // A subclass constructor must first build the Animal part of itself.
    public Dog(String name) {
        // super(name) runs Animal's constructor. It must be the first line;
        // without it this would not compile, because Animal has no
        // constructor that takes no arguments.
        super(name);
    }

    // Overrides Animal's abstract speak(): this is the dog-specific part.
    @Override
    public String speak() {
        // The only thing a Dog needs to say for itself.
        return "Woof";
    }
}
