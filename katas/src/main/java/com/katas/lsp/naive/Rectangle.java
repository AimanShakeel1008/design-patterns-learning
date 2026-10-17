// WRONG-WAY setup, kept on purpose: a mutable rectangle that a Square extends.
package com.katas.lsp.naive;

// A rectangle whose sides can be changed one at a time. Its CONTRACT (the
// promises its users rely on) includes: "setWidth changes the width and
// leaves the height alone", and the same for setHeight.
public class Rectangle {

    // The width, changeable through setWidth.
    private int width;

    // The height, changeable through setHeight.
    private int height;

    // Build a rectangle of the given size.
    public Rectangle(int width, int height) {
        // Store the width.
        this.width = width;
        // Store the height.
        this.height = height;
    }

    // PROMISE: afterwards, getWidth() == width, and the height is unchanged.
    public void setWidth(int width) {
        // Change only the width.
        this.width = width;
    }

    // PROMISE: afterwards, getHeight() == height, and the width is unchanged.
    public void setHeight(int height) {
        // Change only the height.
        this.height = height;
    }

    // Read the width.
    public int getWidth() {
        // Hand back the field.
        return width;
    }

    // Read the height.
    public int getHeight() {
        // Hand back the field.
        return height;
    }

    // Width times height.
    public int area() {
        // The area of a rectangle.
        return width * height;
    }
}
