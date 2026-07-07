package com.katas.oopbasics;

// A SECOND, unrelated class that also implements the SAME Switchable contract.
// A lamp and a fan share nothing in their guts — one makes light, one makes
// wind — yet both can honestly promise "I am Switchable." That is the whole
// point: the contract is shared, the implementation is each class's own business.
public class Fan implements Switchable {

    // The fan's own hidden state: is it spinning?
    private boolean spinning;

    @Override
    public void switchOn() {
        // Turning on, for a fan, means the blades start spinning — a completely
        // different action from the lamp, behind an identical method name.
        this.spinning = true;
        System.out.println("Fan: the blades start spinning.");
    }

    @Override
    public void switchOff() {
        // Turning off means the blades coast to a stop.
        this.spinning = false;
        System.out.println("Fan: the blades slow to a stop.");
    }

    @Override
    public boolean isOn() {
        // For a fan, "on" means "currently spinning."
        return this.spinning;
    }
}
