// The RIGHT WAY, part 2: a rectangle that never changes after creation.
package com.katas.lsp;

// Immutable: its fields are final and there are no setters. "Resizing"
// returns a NEW rectangle, so no promise about one side staying the same
// can ever be broken behind a caller's back.
public class Rectangle implements Shape {

    // The width, fixed for this object's whole life.
    private final int width;

    // The height, fixed for this object's whole life.
    private final int height;

    // PRECONDITION: both sides must be positive. Callers must meet it.
    public Rectangle(int width, int height) {
        // Refuse a rectangle that cannot exist.
        if (width <= 0 || height <= 0) {
            // Loud refusal naming the bad values.
            throw new IllegalArgumentException("Sides must be positive: " + width + "x" + height);
        }
        // Store the width.
        this.width = width;
        // Store the height.
        this.height = height;
    }

    // A new rectangle with a different width and THIS height.
    public Rectangle withWidth(int newWidth) {
        // This object is untouched; the caller gets a fresh one.
        return new Rectangle(newWidth, height);
    }

    // A new rectangle with a different height and THIS width.
    public Rectangle withHeight(int newHeight) {
        // This object is untouched; the caller gets a fresh one.
        return new Rectangle(width, newHeight);
    }

    // Keeps Shape's promise: a positive area.
    @Override
    public int area() {
        // Width times height.
        return width * height;
    }

    // Keeps Shape's promise: one descriptive line.
    @Override
    public String describe() {
        // e.g. "Rectangle 5x4: area 20".
        return "Rectangle " + width + "x" + height + ": area " + area();
    }
}
