// The RIGHT WAY, part 1: only the promises EVERY shape can truly keep.
package com.katas.lsp;

// The contract: every shape has an area and can describe itself. It says
// nothing about changing sides, because squares cannot keep such a promise.
public interface Shape {

    // PROMISE: a positive number, the shape's area.
    int area();

    // PROMISE: one line of text naming the shape, its size and its area.
    String describe();
}
