# Glossary — Terms as We Meet Them

Every design term, principle name, and pattern name is added here the first time it appears in a lesson, with a plain-language definition and the lesson number where it was first explained in full. This page is an **index**, not a replacement — the real, worked explanation always lives inline in the lesson.

Terms are grouped by first-appearance lesson so the list reads in learning order.

---

## Lesson 00 — Setup

- **JDK (Java Development Kit)** — the toolbox that lets you *build* Java programs, not just run them. It includes the compiler (`javac`), the runtime (`java`), and the standard libraries. (Lesson 00)
- **JVM (Java Virtual Machine)** — the program that actually runs compiled Java. Your code is compiled once into "bytecode" and the JVM executes that bytecode on any operating system. (Lesson 00)
- **Bytecode** — the halfway language Java source is compiled into (`.class` files). Not human source, not raw machine code; the JVM reads it. (Lesson 00)
- **Maven** — a build tool that compiles your code, downloads the outside libraries you declare, runs your tests, and packages the result, all with one command. (Lesson 00)
- **POM (`pom.xml`, Project Object Model)** — the single file where you tell Maven your project's name, its Java version, its libraries, and how to build it. (Lesson 00)
- **Dependency** — an outside library your project uses. You *declare* it in the POM and Maven downloads it for you. (Lesson 00)
- **Artifact** — one published, versioned library or build output, identified by its coordinates (groupId, artifactId, version). (Lesson 00)
- **JUnit** — the testing library. You write small methods that check your code behaves as expected; JUnit runs them and reports pass/fail. (Lesson 00)
- **Unit test** — a small automated check that one piece of code does what it should, run with a single command so you catch breakage early. (Lesson 00)
- **Smoke test** — the simplest possible test: it checks the build-and-test machinery itself works, before testing any real logic. (Lesson 00)
- **Kata** — a small, self-contained practice exercise. In this course, one tiny frozen project per pattern, kept forever so you can revisit the pattern in its purest form. (Lesson 00)
- **Git** — the version-control tool that records the history of your files as a series of saved snapshots (commits). (Lesson 00)
- **Repository (repo)** — the folder Git tracks, together with its full saved history. (Lesson 00)
- **Commit** — one saved snapshot of your files in Git, with a message describing the change. (Lesson 00)
- **Remote** — a copy of your repository hosted elsewhere (here, on GitHub) that you push your commits to. (Lesson 00)

---

## Lesson 01 — Objects, Encapsulation, and Interfaces

- **Class** — the blueprint or template for a kind of object; it defines the data and behaviour objects of that kind will have. One class, many objects. (Lesson 01)
- **Object (instance)** — one concrete thing built from a class, bundling data with the behaviour that guards it. `new BankAccount(100)` builds one object. (Lesson 01)
- **Object (as a concept)** — a bundle of data plus the behaviour that acts on and protects that data, kept together in one place. (Lesson 01)
- **Field** — a piece of data stored inside an object (e.g. `balance`). (Lesson 01)
- **Method** — a named piece of behaviour an object can perform (e.g. `withdraw(...)`). (Lesson 01)
- **Constructor** — the special method that runs once when an object is built; its job is to leave the new object in a valid starting state, guarding invariants from the first moment. (Lesson 01)
- **Encapsulation** — hiding an object's data (making fields `private`) behind guarded `public` methods, so the data can only change through behaviour that enforces the rules. (Lesson 01)
- **`private`** — an access keyword: a `private` field or method can be touched only by code inside the same class. The tool of encapsulation. (Lesson 01)
- **`public`** — an access keyword: a `public` field or method can be touched by any code anywhere. The controlled door onto an object. (Lesson 01)
- **Invariant** — a rule that must always be true about an object for its whole life (e.g. "the balance is never negative"). Encapsulation is how you protect it. (Lesson 01)
- **Interface** — a pure contract: a named list of things a class promises it can do, with no code for how. Says *what*, never *how*. (Lesson 01)
- **Contract** — a promise about *what* is provided, with the *how* left open; an interface is a contract written in Java. (Lesson 01)
- **Implementation** — the actual working code that fulfils a contract; `Lamp` is one implementation of the `Switchable` contract. (Lesson 01)
- **`implements`** — the Java keyword by which a class promises (compiler-checked) to fulfil every method an interface lists. (Lesson 01)
- **Program to an interface, not an implementation** — the golden rule: depend on the contract, not on any one concrete class. The most load-bearing habit in the course. (Lesson 01)
- **Polymorphism** — one call site behaving in many ways, decided at run time by which object is actually present. The reward for programming to an interface. (Lesson 01)
- **Abstraction** — hiding *complexity* behind a simple set of operations (distinct from encapsulation, which hides *data*). Introduced briefly; sharpened in Lesson 04. (Lesson 01)
