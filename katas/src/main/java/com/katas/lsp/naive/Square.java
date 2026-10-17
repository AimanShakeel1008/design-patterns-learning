// WRONG WAY: "a square IS a rectangle" is true in geometry, false in code.
package com.katas.lsp.naive;

// A square must keep its four sides equal (its own invariant). To do that,
// it overrides both setters so that changing one side changes both. That
// quietly BREAKS Rectangle's promise "setWidth leaves the height alone".
public class Square extends Rectangle {

    // A square has one side length.
    public Square(int side) {
        // Both sides equal from the start.
        super(side, side);
    }

    // SURPRISING OVERRIDE: also changes the height, which Rectangle promised
    // setWidth would never do.
    @Override
    public void setWidth(int width) {
        // Keep the square square...
        super.setWidth(width);
        // ...by changing the other side too.
        super.setHeight(width);
    }

    // The same surprise in the other direction.
    @Override
    public void setHeight(int height) {
        // Keep the square square...
        super.setWidth(height);
        // ...by changing both sides.
        super.setHeight(height);
    }
}
