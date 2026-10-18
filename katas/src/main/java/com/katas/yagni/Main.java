// The runnable demo for Lesson 11's YAGNI kata.
package com.katas.yagni;

// The over-prepared version, kept frozen in naive/.
import com.katas.yagni.naive.ListPrinter;

// List.of for the items.
import java.util.List;

// Shows that the speculative knobs add nothing but a trap.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // Today's shopping.
        List<String> items = List.of("milk", "eggs");

        // NAIVE: the one setting the app ever uses. print, not println: the text ends with a newline.
        System.out.print(new ListPrinter("plain", false, 1).print(items));
        // One "future" knob compiles fine and fails only when tried.
        try {
            // Two columns, a feature that was planned but never finished.
            new ListPrinter("plain", false, 2).print(items);
        } catch (UnsupportedOperationException e) {
            // Show the refusal instead of crashing the demo.
            System.out.println("Naive printer refused: " + e.getMessage());
        }

        // YAGNI: the same output from a fraction of the code.
        System.out.print(new ShoppingList().print(items));
    }
}
