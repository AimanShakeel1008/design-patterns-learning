// The WRONG way (Lesson 11): machinery far bigger than the job.
package com.katas.kiss.naive;

// The JDK's growable list, for the registered rules.
import java.util.ArrayList;
// The JDK's list interface.
import java.util.List;

// A "flexible greeting engine": a rule interface, one class per part of the
// day, a registry, and a loop, all to choose between a few greetings by the
// hour. It works, but the order that decides the answer is hidden in the
// order of registration, so the first extension goes wrong (see NightRule).
// Kept frozen to show needless complexity. Nested types keep it in one file.
public class GreetingEngine {

    // A plug-in rule: "do you apply at this hour, and what do you say?"
    public interface GreetingRule {

        // True if this rule wants to answer for the given hour (0-23).
        boolean appliesTo(int hour);

        // The words this rule says.
        String greeting();
    }

    // Before noon.
    public static class MorningRule implements GreetingRule {

        // Hours 0 to 11.
        @Override
        public boolean appliesTo(int hour) {
            // Before 12.
            return hour < 12;
        }

        // Its words.
        @Override
        public String greeting() {
            // The morning greeting.
            return "Good morning";
        }
    }

    // Noon until six in the evening.
    public static class AfternoonRule implements GreetingRule {

        // Hours 12 to 17.
        @Override
        public boolean appliesTo(int hour) {
            // From 12, before 18.
            return hour >= 12 && hour < 18;
        }

        // Its words.
        @Override
        public String greeting() {
            // The afternoon greeting.
            return "Good afternoon";
        }
    }

    // From six in the evening: "until midnight", as written on day one.
    public static class EveningRule implements GreetingRule {

        // Hours 18 to 23.
        @Override
        public boolean appliesTo(int hour) {
            // From 18 onwards.
            return hour >= 18;
        }

        // Its words.
        @Override
        public String greeting() {
            // The evening greeting.
            return "Good evening";
        }
    }

    // ADDED LATER for the new requirement "say Good night from 22:00".
    public static class NightRule implements GreetingRule {

        // Hours 22 and 23.
        @Override
        public boolean appliesTo(int hour) {
            // From 22 onwards.
            return hour >= 22;
        }

        // Its words.
        @Override
        public String greeting() {
            // The night greeting.
            return "Good night";
        }
    }

    // The registered rules, asked in the order they were added.
    private final List<GreetingRule> rules = new ArrayList<>();

    // The engine registers its three original rules on creation.
    public GreetingEngine() {
        // First asked: morning.
        rules.add(new MorningRule());
        // Second: afternoon.
        rules.add(new AfternoonRule());
        // Third: evening, which already claims every hour from 18 to 23.
        rules.add(new EveningRule());
    }

    // "Extensible": anyone can add a rule. It goes to the END of the list.
    public void addRule(GreetingRule rule) {
        // Appended after the original three.
        rules.add(rule);
    }

    // Ask each rule in turn; the first that applies wins.
    public String greet(int hour) {
        // Walk the rules in registration order.
        for (GreetingRule rule : rules) {
            // The first rule that claims this hour answers.
            if (rule.appliesTo(hour)) {
                // Its words are the answer.
                return rule.greeting();
            }
        }
        // No rule claimed the hour: refuse loudly.
        throw new IllegalStateException("No greeting rule for hour " + hour);
    }
}
