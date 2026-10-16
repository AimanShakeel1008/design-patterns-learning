// Same package as the classes under test.
package com.katas.inheritance;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves overriding: the ACTUAL object's method runs, whatever the declared type.
class AnimalTest {

    // Inherited introduce() + overridden speak() give each animal its own line.
    @Test
    void eachAnimalIntroducesItselfWithItsOwnSound() {
        // Declared as Animal on purpose: the call site knows only the parent.
        Animal dog = new Dog("Rex");
        // Declared as Animal again, different actual class.
        Animal cat = new Cat("Tom");
        // Dog's speak() is used inside Animal's introduce().
        assertEquals("I am Rex and I say Woof", dog.introduce());
        // Cat's speak() is used inside the very same introduce() code.
        assertEquals("I am Tom and I say Meow", cat.introduce());
    }

    // The lookup walks more than one level, and super.speak() reaches the parent.
    @Test
    void puppyOverridesDogAndStillReachesDogsVersion() {
        // A Puppy held through the top-level type.
        Animal puppy = new Puppy("Bit");
        // Puppy.speak() runs, and its super.speak() runs Dog.speak().
        assertEquals("Yip (learning to Woof)", puppy.speak());
        // introduce() is found in Animal, and its speak() call comes back to Puppy.
        assertEquals("I am Bit and I say Yip (learning to Woof)", puppy.introduce());
    }

    // toString() is overridden once in Animal but reports the actual class.
    @Test
    void toStringNamesTheActualClass() {
        // Declared Animal, actually a Cat.
        Animal cat = new Cat("Tom");
        // getClass() asks the object what it really is.
        assertEquals("Cat Tom", cat.toString());
    }
}
