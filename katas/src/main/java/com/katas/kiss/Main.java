// The runnable demo for Lesson 11's KISS kata.
package com.katas.kiss;

// The over-built version, kept frozen in naive/.
import com.katas.kiss.naive.GreetingEngine;

// Shows the engine's first extension going wrong, and the simple version not.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // NAIVE: build the engine with its three original rules...
        GreetingEngine engine = new GreetingEngine();
        // ...and "extend" it for the new requirement: Good night from 22:00.
        engine.addRule(new GreetingEngine.NightRule());
        // The morning still works.
        System.out.println("Engine at 9:  " + engine.greet(9));
        // But at 23 the older EveningRule is asked first, and it claims the hour.
        System.out.println("Engine at 23: " + engine.greet(23));

        // KISS: the plain version with the new line already in place.
        Greeter greeter = new Greeter();
        // Same morning answer.
        System.out.println("Simple at 9:  " + greeter.greet(9));
        // And the new requirement, honoured.
        System.out.println("Simple at 23: " + greeter.greet(23));
    }
}
