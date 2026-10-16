// House "modes" live in their own package: they are what keeps changing.
package com.smarthome.routines;

// Routines work on devices through the contract only.
import com.smarthome.devices.Device;

// The JDK's list interface.
import java.util.List;

// THE VARIATION POINT: one thing a homeowner can ask the house to do. The
// hub runs any Routine without knowing which one it is, so a new mode is a
// new class, never an edit to the hub.
public interface Routine {

    // Apply this routine to every device in the house.
    void applyTo(List<Device> devices);
}
