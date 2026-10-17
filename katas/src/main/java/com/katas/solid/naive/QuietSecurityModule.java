// WRONG WAY: "quiet at night" bolted on by inheritance, breaking the parent's promise.
package com.katas.solid.naive;

// The JDK's list interface.
import java.util.List;

// Requirement: "don't wake the owner at night". The quick fix extended the
// manager and refused to check at night. Any code holding a SecurityModule
// now crashes at night if it was handed this subclass, and smoke at night
// is silently never reported: an LSP violation with a dangerous side effect.
public class QuietSecurityModule extends SecurityModule {

    // Same construction as the parent.
    public QuietSecurityModule(List<HomeDevice> devices, String channel) {
        // Let the parent store everything.
        super(devices, channel);
    }

    // SURPRISING OVERRIDE + REFUSED BEQUEST: the parent promised to check.
    @Override
    public void checkHouse(boolean nightTime) {
        // At night: refuse the whole job, smoke included.
        if (nightTime) {
            // A caller trusting SecurityModule.checkHouse is broken here.
            throw new UnsupportedOperationException("Quiet hours: checks are switched off at night");
        }
        // By day: behave like the parent.
        super.checkHouse(false);
    }
}
