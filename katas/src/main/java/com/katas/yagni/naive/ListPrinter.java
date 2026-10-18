// The WRONG way (Lesson 11): features built for a future nobody asked for.
package com.katas.yagni.naive;

// The JDK's list interface.
import java.util.List;

// A shopping-list printer built "to be ready for anything": an HTML format,
// numbering, and multi-column layout. The app only ever calls it as
// new ListPrinter("plain", false, 1). Everything else is speculative: never
// used, barely tested, and one knob is a trap that compiles fine and fails only
// when someone finally tries it. Kept frozen to show the cost of guessing.
public class ListPrinter {

    // "plain" or "html". Only "plain" has ever been used.
    private final String format;

    // Numbered lines instead of dashes. Never used.
    private final boolean numbered;

    // How many columns to lay the list out in. Only 1 works.
    private final int columns;

    // Every knob must be chosen by every caller, even though only one setting is ever used.
    public ListPrinter(String format, boolean numbered, int columns) {
        // Remember the format.
        this.format = format;
        // Remember the numbering choice.
        this.numbered = numbered;
        // Remember the column count.
        this.columns = columns;
    }

    // Turn the items into printable text.
    public String print(List<String> items) {
        // The speculative feature nobody finished: it waits to fail at run time.
        if (columns != 1) {
            // Refuse: the "future" arrived before the code did.
            throw new UnsupportedOperationException("Columns not implemented yet");
        }
        // StringBuilder: a growable piece of text.
        StringBuilder text = new StringBuilder();
        // The HTML format needs an opening tag.
        if (format.equals("html")) {
            // Open the list.
            text.append("<ul>\n");
        }
        // One line per item, in one of several styles.
        for (int i = 0; i < items.size(); i++) {
            // The HTML style.
            if (format.equals("html")) {
                // One list element.
                text.append("  <li>").append(items.get(i)).append("</li>\n");
            } else {
                // The plain style: a number or a dash, chosen by another knob.
                String marker = numbered ? (i + 1) + ". " : "- ";
                // The marker, the item, and a line break.
                text.append(marker).append(items.get(i)).append("\n");
            }
        }
        // The HTML format needs a closing tag.
        if (format.equals("html")) {
            // Close the list.
            text.append("</ul>\n");
        }
        // The finished text.
        return text.toString();
    }
}
