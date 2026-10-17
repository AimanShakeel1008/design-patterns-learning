// House "modes" live in their own package: they are what keeps changing.
package com.smarthome.routines;

// Routines need exactly one capability: switching.
import com.smarthome.devices.Switchable;

// The JDK's list interface.
import java.util.List;

// THE VARIATION POINT: one thing a homeowner can ask the house to do. The
// hub runs any Routine without knowing which one it is, so a new mode is a
// new class, never an edit to the hub. Since Lesson 08 routines receive only
// SWITCHABLE devices, so "Away" can never be handed a smoke alarm or a lock.
public interface Routine {

    // Apply this routine to every switchable device in the house.
    void applyTo(List<Switchable> devices);
}
