// A second concrete animal, as small as Dog.
package com.katas.inheritance;

// A Cat IS an Animal: it inherits everything shared and adds its sound.
public class Cat extends Animal {

    // Build the Animal part first, exactly as Dog does.
    public Cat(String name) {
        // Hand the name up to Animal's constructor.
        super(name);
    }

    // Overrides Animal's abstract speak() with the cat-specific sound.
    @Override
    public String speak() {
        // The only thing a Cat needs to say for itself.
        return "Meow";
    }
}
