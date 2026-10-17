// Same package as the right-way shapes.
package com.katas.lsp;

// The @Test marker.
import org.junit.jupiter.api.Test;

// List.of for a list of shapes.
import java.util.List;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;
// Fails unless running the code throws the named exception type.
import static org.junit.jupiter.api.Assertions.assertThrows;

// Proves every shape keeps exactly the promises it makes.
class ShapeTest {

    // "Changing" one side of a rectangle leaves the other side alone.
    @Test
    void rectangleResizesOneSideAtATime() {
        // 2x3, then width 5, then height 4.
        Rectangle rectangle = new Rectangle(2, 3).withWidth(5).withHeight(4);
        // 5 x 4.
        assertEquals("Rectangle 5x4: area 20", rectangle.describe());
    }

    // A square is always square, because it only ever has one side.
    @Test
    void squareStaysSquare() {
        // Side 2, then side 4.
        Square square = new Square(2).withSide(4);
        // 4 x 4.
        assertEquals("Square 4x4: area 16", square.describe());
    }

    // The client that relies only on Shape works for every honest Shape.
    @Test
    void anyShapeCanBeSubstitutedWhereAShapeIsExpected() {
        // A 5x4 rectangle and a 4x4 square, mixed.
        List<Shape> shapes = List.of(new Rectangle(5, 4), new Square(4));
        // 20 + 16.
        assertEquals(36, new AreaTotaller().totalArea(shapes));
    }

    // The precondition: impossible sizes are refused.
    @Test
    void nonPositiveSidesAreRefused() {
        // A zero-width rectangle cannot exist.
        assertThrows(IllegalArgumentException.class, () -> new Rectangle(0, 3));
        // Nor can a negative square.
        assertThrows(IllegalArgumentException.class, () -> new Square(-1));
    }
}
