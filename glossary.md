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
