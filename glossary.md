# Glossary — Terms as We Meet Them

Every design term, principle name, and pattern name is added here the first time it appears in a lesson, with a plain-language definition and the lesson number where it was first explained in full. This page is an **index**, not a replacement — the real, worked explanation always lives inline in the lesson page (`lessons/`). The one-line definitions here are also the text shown by the lessons' hover-to-define terms.

Terms are grouped by first-appearance lesson so the list reads in learning order.

---

## Lesson 00 — Getting Your Machine Ready

- **Compiling / compiler** — translating human-readable code into a form a machine can run; the program that does it is the compiler (Java's is `javac`). (Lesson 00)
- **Bytecode** — The halfway language Java source is compiled into (.class files); not human source, not raw machine code — the JVM reads it. (Lesson 00)
- **JVM (Java Virtual Machine)** — the program that runs bytecode on a real computer; each operating system has its own, so the same `.class` file runs anywhere. (Lesson 00)
- **Standard library** — Java's ready-made building blocks (text, lists, maps, files…) that ship with every JDK. (Lesson 00)
- **JDK (Java Development Kit)** — the whole toolbox: the tools that build Java (`javac`, `java`, `jar`) plus the runtime that runs it. (Lesson 00)
- **JRE (Java Runtime Environment)** — the old name for just the runtime half of the JDK (JVM + standard library); today you simply install the JDK. (Lesson 00)
- **LTS (Long-Term Support)** — a Java version promised fixes for many years, so it is a safe one to stand on; Java 21 is one. (Lesson 00)
- **Environment variable** — a named setting your computer keeps that any program can read. (Lesson 00)
- **PATH** — the environment variable listing folders the terminal searches, in order, when you type a command; the first match wins. (Lesson 00)
- **`JAVA_HOME`** — the environment variable naming one JDK folder directly; Maven uses it when set, and falls back to the PATH when not. (Lesson 00)
- **Build tool** — a program that compiles code, fetches libraries, runs tests, and packages the result from one command. (Lesson 00)
- **Maven** — the build tool this course uses; it follows the work order in each project's `pom.xml`. (Lesson 00)
- **Dependency** — an outside library your project uses; you declare it in the POM and Maven downloads it. (Lesson 00)
- **POM (`pom.xml`, Project Object Model)** — the one file telling Maven a project's name, Java version, dependencies, and plugins. (Lesson 00)
- **Coordinates** — the three-part address of a project or library: `groupId` (owner), `artifactId` (name), `version`. (Lesson 00)
- **Artifact** — one published, versioned thing (usually a library `.jar`) identified by its coordinates. (Lesson 00)
- **Plugin** — a small add-on tool that does the real work of one Maven step (compiling, running tests, running `main`). (Lesson 00)
- **Surefire** — the Maven plugin that finds and runs the tests during `mvn test`. (Lesson 00)
- **Local repository (`~/.m2`)** — the folder where Maven caches every downloaded artifact so later builds reuse it. (Lesson 00)
- **IDE (Integrated Development Environment)** — a code editor that understands Java: colouring, error-spotting, suggestions, one-click runs. (Lesson 00)
- **Version control** — recording the history of your files as saved snapshots so you can always go back. (Lesson 00)
- **Git** — the version-control tool this course uses. (Lesson 00)
- **Repository (repo)** — the folder Git watches, together with its whole saved history. (Lesson 00)
- **Commit** — one saved snapshot in Git, with a message describing what changed. (Lesson 00)
- **Remote** — a copy of your repository hosted elsewhere (here, on GitHub). (Lesson 00)
- **Push** — sending your new commits up to the remote. (Lesson 00)
- **`.gitignore`** — the file listing paths Git must never save (build output, local-only files). (Lesson 00)
- **`.gitattributes` / line endings** — the file that tells Git to store one line-ending style (LF), so Windows and Linux never see phantom changes. (Lesson 00)
- **Package** — the namespace a Java class lives in, written on its first line and mirrored by its folders (e.g. `com.katas`). (Lesson 00)
- **`main` method** — `public static void main(String[] args)`, the exact starting point the JVM calls first. (Lesson 00)
- **Unit test** — A small automated check that one piece of code does what it should, run with a single command so breakage is caught early. (Lesson 00)
- **JUnit** — the testing library that finds and runs unit tests and reports pass or fail. (Lesson 00)
- **Annotation** — a tag stuck onto code that tools read (e.g. `@Test` tells JUnit "run this as a test"). (Lesson 00)
- **Assertion** — a line in a test stating what must be true; the test fails if it is not (e.g. `assertEquals(4, 2 + 2)`). (Lesson 00)
- **Smoke test** — the simplest possible test: it proves the build-and-test machinery works, not any real logic. (Lesson 00)
- **Kata** — a small practice exercise you repeat until it is second nature; here, one tiny frozen example per pattern. (Lesson 00)
- **Maven Central** — the free public store of open-source Java libraries, fetched by coordinates. (Lesson 00)
- **Continuous integration (CI)** — a clean server that runs the same build and tests on every push, so "works on my machine" is not enough. (Lesson 00)
- **Multi-module build** — one parent POM building several Maven projects at once; deliberately not used in this course. (Lesson 00)
