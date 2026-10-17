// A client of the Shape contract: any honest Shape can be substituted.
package com.katas.lsp;

// The JDK's list interface.
import java.util.List;

// Relies only on Shape's promises, which every implementation keeps, so it
// works unchanged for rectangles, squares, and shapes not invented yet.
public class AreaTotaller {

    // Add up the areas of any shapes.
    public int totalArea(List<Shape> shapes) {
        // Start from zero...
        int total = 0;
        // ...and add each shape's area, whatever kind it is.
        for (Shape shape : shapes) {
            // Each shape keeps the promise "area() is its area".
            total += shape.area();
        }
        // The sum.
        return total;
    }
}
