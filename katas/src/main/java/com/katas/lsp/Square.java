// The RIGHT WAY, part 3: a square is a Shape, NOT a Rectangle.
package com.katas.lsp;

// A sibling of Rectangle under Shape. It promises only what it can keep
// (an area, a description), so it can stand in wherever a Shape is
// expected, and nobody can ever hand it to code that expects to set one
// side independently.
public class Square implements Shape {

    // The one side length, fixed for this object's whole life.
    private final int side;

    // PRECONDITION: the side must be positive.
    public Square(int side) {
        // Refuse a square that cannot exist.
        if (side <= 0) {
            // Loud refusal naming the bad value.
            throw new IllegalArgumentException("Side must be positive: " + side);
        }
        // Store the side.
        this.side = side;
    }

    // A new square with a different side.
    public Square withSide(int newSide) {
        // This object is untouched; the caller gets a fresh one.
        return new Square(newSide);
    }

    // Keeps Shape's promise: a positive area.
    @Override
    public int area() {
        // Side times side.
        return side * side;
    }

    // Keeps Shape's promise: one descriptive line.
    @Override
    public String describe() {
        // e.g. "Square 4x4: area 16".
        return "Square " + side + "x" + side + ": area " + area();
    }
}
