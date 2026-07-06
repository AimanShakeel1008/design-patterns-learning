# Design Patterns and SOLID Principles in Java — A Learning Journey

This repository is a complete, hands-on course in object-oriented design: the **SOLID principles**, the classic **Gang of Four design patterns**, and — just as important — the judgment to know when a pattern helps and when it hurts.

Everything is **plain Java 21**, deliberately with **no Spring or any framework**, so every pattern is seen bare, with nothing hidden.

The guiding idea of the whole course:

> **A pattern is a named cure for a specific pain.**
> If you cannot name the pain, you do not yet need the pattern.

Every pattern here is taught *pain first*: we start from working-but-rotting code, make the pain concrete, and only then introduce the pattern as its cure. Equal weight goes to **when NOT to use** each pattern.

---

## What's in this repo

| Folder / file | What it is |
|---|---|
| `lessons/` | The written lessons, in Markdown, organized by phase and chapter. |
| `katas/` | One tiny, self-contained Maven project per pattern (the wrong way, the right way, a demo, and a test). Frozen once written. |
| `smarthome/` | The **Smart Home Hub** — one real, evolving Maven application that grows as patterns are applied where it genuinely needs them. |
| `pattern-catalog.md` | The living cheat-sheet: one quick-reference entry per principle and pattern. |
| `glossary.md` | Every design term, defined in plain language, in the order we meet it. |
| `troubleshooting.md` | Common errors and their fixes. |

---

## The two code homes

**The katas** are frozen exhibits — each one shows a single pattern in its smallest, purest form, and is never edited by later lessons.

**The Smart Home Hub** is the one live project: a hub that controls devices (lights, thermostats, locks, cameras, speakers), reacts to sensors, runs scenes and schedules, and integrates third-party devices. It evolves lesson by lesson as real needs pull in real patterns.

---

## How to build and run

You need **Java 21** and **Maven** installed (see `lessons/phase-0-oop-foundations/chapter-00-setup`).

Run the katas' tests:

```bash
cd katas
mvn test
```

Run the Smart Home Hub's tests:

```bash
cd smarthome
mvn test
```

Run a project's `main`:

```bash
cd smarthome
mvn compile exec:java -Dexec.mainClass=com.smarthome.Main
```

*(Both projects are pinned to Java 21 and JUnit 5. Right now each contains a smoke test that confirms the toolchain works; real code arrives from Lesson 01 onward.)*

---

## Roadmap

- **Phase 0** — OOP foundations (objects, encapsulation, interfaces, inheritance, composition, coupling, UML)
- **Phase 1** — The SOLID principles
- **Phase 2** — Creational patterns
- **Phase 3** — Structural patterns
- **Phase 4** — Behavioral patterns
- **Phase 5** — Beyond the Gang of Four (Null Object, DI, Repository, MVC, modern Java, anti-patterns)
- **Phase 6** — Capstone: the whole hub reviewed, a grand refactoring, and the completed catalog

This page grows as the hub gains visible capabilities.
