// Same package as the classes under test.
package com.katas.inheritance;

// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// Proves overloading: the DECLARED type of the argument picks the method.
class VetTest {

    // A Dog-typed argument selects examine(Dog).
    @Test
    void dogTypedArgumentGetsTheDogCheckUp() {
        // The vet under test.
        Vet vet = new Vet();
        // Declared type Dog.
        Dog rex = new Dog("Rex");
        // The compiler chose examine(Dog) for this call.
        assertEquals("Dog check-up for Rex", vet.examine(rex));
    }

    // The trap, pinned down: same object, Animal-typed variable, other method.
    @Test
    void sameDogThroughAnAnimalVariableGetsTheGeneralCheckUp() {
        // The vet under test.
        Vet vet = new Vet();
        // Declared type Animal; the actual object is still a Dog.
        Animal sameRex = new Dog("Rex");
        // The compiler only saw "Animal", so examine(Animal) was chosen.
        assertEquals("General check-up for Rex", vet.examine(sameRex));
    }
}
