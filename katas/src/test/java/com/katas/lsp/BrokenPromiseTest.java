// The test sits beside the right-way shapes; it reaches the naive classes by import.
package com.katas.lsp;

// The naive classes: a mutable Rectangle, a Square that extends it, and an
// honest client of Rectangle's contract.
import com.katas.lsp.naive.Resizer;
// The @Test marker.
import org.junit.jupiter.api.Test;

// Equality check.
import static org.junit.jupiter.api.Assertions.assertEquals;

// PINS THE SYMPTOM on purpose: the same client gets a different answer when
// handed a Square, so Square is not substitutable for Rectangle.
class BrokenPromiseTest {

    // 20 for a real rectangle, 16 for a "rectangle" that is a square.
    @Test
    void squareBreaksRectanglesPromiseInsideAnHonestClient() {
        // One honest client.
        Resizer resizer = new Resizer();
        // A real rectangle: the promise holds.
        assertEquals(20, resizer.stretchTo5By4(new com.katas.lsp.naive.Rectangle(2, 3)));
        // A Square passed as a Rectangle: setHeight(4) also changed the width.
        assertEquals(16, resizer.stretchTo5By4(new com.katas.lsp.naive.Square(2)));
    }
}
