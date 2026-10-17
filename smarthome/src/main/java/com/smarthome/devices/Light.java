// Same package as the contracts it fulfils.
package com.smarthome.devices;

// A light: Switchable (through SwitchableDevice) AND Dimmable. One class can
// sign several small contracts; each client sees only the one it needs.
public class Light extends SwitchableDevice implements Dimmable {

    // Brightness as a percentage; a new light is at full brightness.
    private int brightness = 100;

    // Build a light with its name.
    public Light(String name) {
        // Hand the name to SwitchableDevice's constructor, which stores it.
        super(name);
    }

    // Keeps Dimmable's contract: 0..100, otherwise refuse and change nothing.
    @Override
    public void setBrightness(int percent) {
        // Refuse a percentage that cannot exist.
        if (percent < 0 || percent > 100) {
            // Loud refusal naming the bad value.
            throw new IllegalArgumentException("Brightness must be 0-100%, got " + percent);
        }
        // Valid, so store it.
        this.brightness = percent;
    }

    // Keeps Dimmable's contract: always 0..100.
    @Override
    public int getBrightness() {
        // Hand back the field.
        return brightness;
    }

    // The one thing a light must say for itself.
    @Override
    public String status() {
        // Off: e.g. "Living Room Light: OFF".
        if (!isOn()) {
            // Name first, as the Device contract promises.
            return getName() + ": OFF";
        }
        // On at full brightness: "ON", exactly as before dimming existed.
        if (brightness == 100) {
            // Unchanged wording, so older reports and tests still hold.
            return getName() + ": ON";
        }
        // On and dimmed: e.g. "Living Room Light: ON at 40%".
        return getName() + ": ON at " + brightness + "%";
    }
}
