// A CLIENT written honestly against Rectangle's promises.
package com.katas.lsp.naive;

// This code is correct for every object that keeps Rectangle's contract.
// It was never wrong; it is the Square that broke the deal.
public class Resizer {

    // Make any rectangle 5 wide and 4 high, then report its area.
    public int stretchTo5By4(Rectangle rectangle) {
        // Promise: changes the width only.
        rectangle.setWidth(5);
        // Promise: changes the height only.
        rectangle.setHeight(4);
        // By Rectangle's contract this must be 5 x 4 = 20.
        return rectangle.area();
    }
}
