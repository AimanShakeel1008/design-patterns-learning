// The runnable demo for Lesson 02's kata.
package com.katas.inheritance;

// Shows overriding (decided at run time by the actual object) and then
// overloading (decided at compile time by the declared type).
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // PART 1 - polymorphism through a parent class. The array's declared
        // element type is Animal; the actual objects are three different classes.
        Animal[] animals = { new Dog("Rex"), new Cat("Tom"), new Puppy("Bit") };
        // One loop, one call site, three behaviours.
        for (Animal animal : animals) {
            // introduce() lives only in Animal, but its speak() call runs the
            // ACTUAL object's speak(): Dog's, Cat's, then Puppy's.
            System.out.println(animal.introduce());
        }
        // println calls toString(); Animal's override uses the actual class name.
        System.out.println(animals[2]);

        // PART 2 - overloading. ONE dog object, referred to by two variables
        // with two different DECLARED types.
        Vet vet = new Vet();
        // Declared type Dog.
        Dog rex = new Dog("Rex");
        // Declared type Animal, but the very same object (a Dog is an Animal).
        Animal sameRex = rex;
        // The compiler sees a Dog argument, so it picks examine(Dog).
        System.out.println(vet.examine(rex));
        // The compiler sees an Animal argument, so it picks examine(Animal),
        // even though the object is a Dog. THIS is the classic trap.
        System.out.println(vet.examine(sameRex));
    }
}
