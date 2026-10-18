// The YAGNI way (Lesson 11): build what is needed today, and nothing more.
package com.katas.yagni;

// The JDK's list interface.
import java.util.List;

// Prints a shopping list the one way the app actually uses: a dash per item.
// No formats, no numbering, no columns. When a real new need arrives, it is
// added then, shaped by that real need instead of by a guess.
public class ShoppingList {

    // Turn the items into printable text, one dashed line each.
    public String print(List<String> items) {
        // StringBuilder: a growable piece of text.
        StringBuilder text = new StringBuilder();
        // Every item in order.
        for (String item : items) {
            // A dash, the item, and a line break.
            text.append("- ").append(item).append("\n");
        }
        // The finished text.
        return text.toString();
    }
}
