// One line of an order: "2 x Margherita".
package com.katas.coupling;

// An OrderLine HOLDS A REFERENCE to a MenuItem (it needs its price), but the
// item is not part of the line; it belongs to the menu. That plain "knows
// about" link is an ASSOCIATION in the diagram.
public class OrderLine {

    // Which item was ordered: the association.
    private final MenuItem item;

    // How many of it.
    private final int quantity;

    // Package-private constructor (no access keyword): only classes in this
    // package, in practice Order, can create lines.
    OrderLine(MenuItem item, int quantity) {
        // Refuse nonsense quantities, so every line is valid from birth.
        if (quantity <= 0) {
            // Loud refusal naming the bad value.
            throw new IllegalArgumentException("Quantity must be positive: " + quantity);
        }
        // Remember the item.
        this.item = item;
        // Remember the quantity.
        this.quantity = quantity;
    }

    // How many items this line contributes to the order.
    int quantity() {
        // Hand back the quantity.
        return quantity;
    }

    // This line's cost: price times quantity.
    int subtotal() {
        // Ask the associated item for its price.
        return item.getPrice() * quantity;
    }
}
