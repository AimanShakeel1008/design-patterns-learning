// The runnable demo for Lesson 07's kata.
package com.katas.lsp;

// List.of to build a list of shapes.
import java.util.List;

// Shows a Square breaking Rectangle's promise, then a design where every
// substitute keeps every promise it makes.
public class Main {

    // The JVM starts here when you run this class.
    public static void main(String[] args) {
        // NAIVE: one honest client, two objects that both claim to be Rectangles.
        com.katas.lsp.naive.Resizer resizer = new com.katas.lsp.naive.Resizer();
        // A real rectangle keeps the promise: 5 x 4 = 20.
        System.out.println("Naive rectangle stretched to 5x4: area "
                + resizer.stretchTo5By4(new com.katas.lsp.naive.Rectangle(2, 3)));
        // A "rectangle" that is really a Square breaks it: 4 x 4 = 16.
        System.out.println("Naive square stretched to 5x4: area "
                + resizer.stretchTo5By4(new com.katas.lsp.naive.Square(2)));

        // RIGHT WAY: resizing a rectangle makes a new one; squares are siblings.
        Rectangle rectangle = new Rectangle(2, 3).withWidth(5).withHeight(4);
        // A square sized directly.
        Square square = new Square(2).withSide(4);
        // Each describes itself truthfully.
        System.out.println(rectangle.describe());
        // The square's own description.
        System.out.println(square.describe());
        // A client that needs only Shape's promises accepts both.
        System.out.println("Total area of both: " + new AreaTotaller().totalArea(List.of(rectangle, square)));
    }
}
