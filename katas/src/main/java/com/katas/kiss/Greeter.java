// The KISS way (Lesson 11): the simplest code that does the whole job.
package com.katas.kiss;

// Chooses a greeting by the hour. The whole rule, in its deciding order, fits
// on one screen: four lines, read top to bottom. The "Good night" requirement
// was one new line, placed where its order is plain to see.
public class Greeter {

    // The greeting for an hour of the day (0-23).
    public String greet(int hour) {
        // Before noon.
        if (hour < 12) {
            // Morning.
            return "Good morning";
        }
        // Noon until 17:59.
        if (hour < 18) {
            // Afternoon.
            return "Good afternoon";
        }
        // 18:00 until 21:59.
        if (hour < 22) {
            // Evening.
            return "Good evening";
        }
        // Everything later: the requirement added in this lesson.
        return "Good night";
    }
}
