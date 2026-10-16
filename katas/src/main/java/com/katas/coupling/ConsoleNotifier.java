// One channel: printing to the console.
package com.katas.coupling;

// ConsoleNotifier IMPLEMENTS Notifier: the IMPLEMENTATION arrow in the diagram.
// The console coupling now lives in this one tiny class, nowhere else.
public class ConsoleNotifier implements Notifier {

    // Deliver by printing.
    @Override
    public void send(String message) {
        // The only line in the kata that knows about System.out.
        System.out.println(message);
    }
}
