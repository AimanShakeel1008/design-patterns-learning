# Lesson 00 — Getting Your Machine Ready

**Phase 0 — OOP Foundations · Chapter 00 — Setup · Lesson 00 (the very first lesson)**

**Files created this lesson:**
- `katas/pom.xml`, `katas/src/main/java/com/katas/Main.java`, `katas/src/test/java/com/katas/SmokeTest.java`
- `smarthome/pom.xml`, `smarthome/src/main/java/com/smarthome/Main.java`, `smarthome/src/test/java/com/smarthome/SmokeTest.java`
- `pattern-catalog.md`, `glossary.md`, `README.md`, `troubleshooting.md` (course documents, seeded)
- `.gitignore` (already present)

**Prerequisites:** none. This is where everything begins.

---

Welcome. Before we can talk about design at all, your machine needs the tools to build and run Java, and this repository needs its skeleton. That is the whole job of this lesson. There is no design pattern here yet — just a clean, working workshop. Every new word gets explained the first time it appears, and by the end you will have two Maven projects that compile and whose tests pass.

A note on how this course runs: **you run everything, I run nothing.** I write the files and tell you the exact command to type. Whenever I predict what a command will print, treat it as a *prediction* — then run the real thing and compare. If they differ, your machine is right and that mismatch is itself a lesson.

---

## 1. The tools you need, and what each one is for

You need four things installed. Let me define each in plain language before you install it, because several of these words come back constantly.

### The JDK — the thing that builds Java

Java source code (the text you write) cannot run directly. It first has to be **compiled** — translated — into a lower-level form.

- **Compiling** means translating human-readable source code into a form the machine can execute. The Java compiler is a program called `javac`.
- That lower-level form is called **bytecode**: not human source, not raw machine code, but a halfway language stored in `.class` files.
- The program that runs bytecode is the **JVM (Java Virtual Machine)**. It reads bytecode and executes it. The clever part: the same bytecode runs on Windows, macOS, or Linux, because each has its own JVM. You compile once; it runs anywhere.
- The **JDK (Java Development Kit)** is the full toolbox: the compiler `javac`, the runtime launcher `java`, the JVM, and the standard libraries. If you only want to *run* Java you could get by with less, but we are *building*, so we need the full JDK.

We pin **Java 21**, which is an **LTS** release. LTS means **Long-Term Support**: a version the maintainers keep patching for years, so it stays a stable, safe target. Pinning one version means the code and predicted output stay reproducible for the whole course.

**Install it, then confirm:**

```bash
java -version
```

**Predicted output (yours will differ in the exact patch number, but the major version must be 21):**

```text
openjdk version "21.0.x" 2025-xx-xx
OpenJDK Runtime Environment (build 21.0.x+xx)
OpenJDK 64-Bit Server VM (build 21.0.x+xx, mixed mode, sharing)
```

The number after `version` must begin with **21**. If it says 17, 8, or anything else, your machine has an older Java in front; see `troubleshooting.md`.

### Maven — the thing that drives the build

You *could* call `javac` by hand for every file, manage libraries by hand, and run tests by hand. Nobody does. **Maven** is a **build tool**: one command compiles your code, downloads the outside libraries you asked for, runs your tests, and packages the result.

Two words Maven forces on us immediately:

- A **dependency** is an outside library your project uses. You don't go find it and copy it in; you *declare* it, and Maven downloads it. Our only dependency in this course is the testing library, JUnit.
- Maven is configured by one file per project called the **POM** — the **Project Object Model**, written as `pom.xml`. It states the project's name, its Java version, its dependencies, and how to build. We will read ours line by line below.

**Install Maven, then confirm:**

```bash
mvn -version
```

**Predicted output (versions will vary; the key line is that it reports Java 21):**

```text
Apache Maven 3.9.x
Maven home: ...
Java version: 21.0.x, vendor: ...
```

If the "Java version" line here says anything other than 21, Maven is using the wrong JDK — the fix is in `troubleshooting.md`.

### An IDE — where you'll actually type

An **IDE (Integrated Development Environment)** is an editor built for code: it highlights syntax, autocompletes, and runs builds with a click. Either is fine for this course:

- **IntelliJ IDEA Community Edition** — free, and the smoothest Java experience.
- **VS Code** with the "Extension Pack for Java" — lighter, also excellent.

The IDE is a convenience. Everything in this course also works from a plain terminal, and the commands I give you are terminal commands so they never depend on which IDE you picked.

### Git and GitHub — where your history lives

- **Git** is a **version-control** tool: it records the history of your files as a series of snapshots. Each snapshot is a **commit**, saved with a short message describing what changed.
- A **repository** (or **repo**) is the folder Git watches, together with its whole saved history.
- **GitHub** is a website that hosts a copy of your repository online. That hosted copy is called a **remote**. You **push** your commits to the remote to back them up and share them.

**Install Git, confirm it, create a free GitHub account, and make one empty repository** there (call it something like `design-patterns-learning`). Do **not** let GitHub add a README or `.gitignore` for you — we control those locally.

```bash
git --version
```

**Predicted output:**

```text
git version 2.xx.x
```

---

## 2. The shape of the repository

Here is the skeleton this lesson puts in place. You do not have to memorize it; it is here so the layout has a reason behind every folder.

```text
design-patterns-learning/          <- the Git repository root
    .gitignore                     <- names files Git must never save
    CLAUDE.md                      <- course rules (local only, never committed)
    curriculum.md                  <- what we teach, in order (local only)
    progress-tracker.md            <- where we are (local only)
    glossary.md                    <- every term, defined (committed)
    pattern-catalog.md             <- the cheat-sheet, one entry per pattern (committed)
    README.md                      <- the repo's front page (committed)
    troubleshooting.md             <- errors and fixes (committed)
    lessons/                       <- the written lessons, Markdown only
        phase-0-oop-foundations/
            chapter-00-setup/
                lesson-00-setup.md <- you are reading it
    katas/                         <- one tiny frozen project per pattern
        pom.xml
        src/main/java/com/katas/Main.java
        src/test/java/com/katas/SmokeTest.java
    smarthome/                     <- the one real, evolving project
        pom.xml
        src/main/java/com/smarthome/Main.java
        src/test/java/com/smarthome/SmokeTest.java
```

Two ideas to hold onto:

1. **`katas/` and `smarthome/` are two different homes.** The **katas** are frozen exhibits — each shows one pattern in its smallest form and is never touched again. The **Smart Home Hub** is one living application we grow across the course. Keeping them apart means you can always revisit a pattern in isolation *and* see it working inside a real system.
2. **The `src/main/java` and `src/test/java` split is Maven's convention.** Your real code goes under `src/main/java`; your tests go under `src/test/java`, mirroring the same package folders. Maven expects exactly this and needs no extra configuration when you follow it. The folders `com/katas` and `com/smarthome` on disk are the **package** — Java's namespace — and they must match the `package` line at the top of each file.

---

## 3. The files, line by line

Everything below is already saved to disk. Here is what each file says and *why*.

### The `.gitignore`

This file lists paths Git must never save. It was written before the first commit for a reason: some files are **local-only** and must never leave your machine.

```gitignore
# Local-only course control files - never commit these
CLAUDE.md
curriculum.md
progress-tracker.md

# Maven / Java build output
target/
*.class

# IDEs
.idea/
*.iml
.vscode/

# Local Claude Code settings - machine-specific, never commit
.claude/
```

- `CLAUDE.md`, `curriculum.md`, `progress-tracker.md` — these three run the course on your machine; they are not course *content* and stay local.
- `target/` and `*.class` — Maven compiles into a `target/` folder. Compiled output is throwaway; you never save build output to Git, only the source that produces it.
- `.idea/`, `*.iml`, `.vscode/` — IDE settings, specific to your machine.
- `.claude/` — local tool settings.

### `katas/pom.xml` — read it slowly, it teaches how Maven thinks

> Saved: `katas/pom.xml`

The full file is on disk with a comment on every part. The pieces that matter:

- The **coordinates** — `groupId` (`com.katas`, who owns it), `artifactId` (`design-pattern-katas`, this project's name), and `version` (`1.0.0`) — are the project's unique "address." Every Java library in the world is identified by exactly these three, which is how Maven can go fetch one for you.
- `<packaging>jar</packaging>` — build the result as a `.jar`, a single zipped bundle of `.class` files.
- The `<properties>` block pins the versions once so every lesson builds the same way: `maven.compiler.release` is `21` (compile the language *and* target the runtime at Java 21), and `junit.version` is `5.14.4`.
- The one `<dependency>` is **JUnit 5** (the "Jupiter" flavour), our testing library. Its `<scope>test</scope>` means it is used only while testing and is never bundled into the app.
- The `maven-surefire-plugin` (version `3.5.5`) is the piece that actually *runs* the tests during `mvn test`. A modern 3.x version is required — older defaults only understand JUnit 4 and would silently run zero tests.

The `smarthome/pom.xml` is the same file with `com.smarthome` coordinates. Deliberately, the **only** dependency in either project is JUnit — no Spring, no framework — so every pattern is seen bare.

### The four Java files

Two are entry points, two are smoke tests.

`Main.java` (one per project) exists only to prove the project compiles and runs. A `main` method is the special method the JVM calls first; its signature — `public static void main(String[] args)` — must be exactly that or the JVM won't recognise it. Each `Main` just prints one line.

The **smoke tests** deserve their name. A **smoke test** is the simplest possible test: it does not check real logic, it just confirms the build-and-test machinery works. (The name comes from powering on a device and checking no smoke pours out.) Ours assert something trivially true (`2 + 2 == 4`). If they pass, Maven, JUnit, the compiler, and the Surefire plugin are all proven healthy — which is exactly what we want to know on day one.

A couple of terms these files introduce:

- An **annotation** is a tag attached to code that tools can read. `@Test` is an annotation that tells JUnit "run this method as a test."
- An **assertion** is a check inside a test. `assertEquals(4, 2 + 2)` fails the test if the two values differ. A test with no failed assertion passes.

---

## 4. What happens when you build — traced step by step

Let's walk through `mvn test` in the `katas/` project as a numbered sequence, so the magic word "build" becomes concrete.

1. You type `mvn test` inside the `katas/` folder.
2. Maven reads `pom.xml` to learn the project's Java version, dependencies, and plugins.
3. It checks its local cache (the `.m2` folder in your home directory) for JUnit and the plugins. **The first time ever**, they aren't cached, so Maven downloads them — you'll see `Downloading...` lines. Every later run reuses the cache and is fast.
4. Maven compiles everything under `src/main/java` with `javac`, targeting Java 21, into the `target/` folder as `.class` bytecode.
5. Maven compiles everything under `src/test/java` the same way.
6. The Surefire plugin scans the compiled test classes, finds every method marked `@Test`, and runs each one.
7. `SmokeTest.toolchain_is_wired_up()` runs. `assertEquals(4, 2 + 2)` holds, so the test passes.
8. Maven prints a results summary and `BUILD SUCCESS`.

**Predicted output (trimmed; the exact lines and timings will vary):**

```text
[INFO] Scanning for projects...
[INFO] Building design-pattern-katas 1.0.0
...
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.katas.SmokeTest
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] Results:
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] BUILD SUCCESS
```

The two lines that matter: **`Tests run: 1, Failures: 0`** and **`BUILD SUCCESS`**.

---

## 5. Run it yourself, and compare

Run each of these and check the result against the prediction above. **If what you see differs, your machine is right — tell me what you saw and we'll work out why.**

Katas tests:

```bash
cd katas
mvn test
```

Smart Home tests:

```bash
cd smarthome
mvn test
```

You should see `Tests run: 1, Failures: 0` and `BUILD SUCCESS` in each. If instead you hit a "not recognized," a wrong Java version, or "no tests were run," open `troubleshooting.md` — those exact symptoms are decoded there.

Optionally, run a `main` to see its banner print:

```bash
cd smarthome
mvn compile exec:java -Dexec.mainClass=com.smarthome.Main
```

**Predicted line in the output:** `Smart Home Hub is starting up...`
*(Note: the `exec:java` goal needs the exec plugin. If Maven reports it can't find `exec:java`, don't worry — running the tests is the check that matters this lesson, and we'll wire up richer run commands when the hub actually does something.)*

---

## 6. The first commit

Your repository already has an initial commit. From here on, each lesson ends by saving your progress to Git and pushing it to GitHub. The commands are always the same three. **I never run these — you do.**

First, one-time only, connect your local repo to the empty GitHub repo you created (replace the URL with yours):

```bash
git remote add origin https://github.com/<your-username>/<your-repo>.git
git branch -M main
```

Then, the end-of-lesson ritual you'll repeat every time:

```bash
cd design-patterns-learning
git add .
git commit -m "chore: scaffold katas and smarthome Maven projects; seed course docs"
git push -u origin main
```

`git add .` stages every changed file **except** the ones named in `.gitignore` — which is exactly why `CLAUDE.md`, `curriculum.md`, and `progress-tracker.md` stay on your machine and never reach GitHub. After the first `push -u`, later pushes are just `git push`.

---

## 7. Key takeaways

- **The JDK builds, the JVM runs, Maven orchestrates.** Source is compiled to bytecode by `javac`, executed by the JVM, and the whole build (compile, fetch libraries, test, package) is driven by Maven reading one `pom.xml`.
- **Versions are pinned on purpose** — Java 21 (LTS) and JUnit 5.14.4 — so the code and its predicted output stay reproducible across every lesson.
- **Two code homes, two jobs:** frozen katas isolate each pattern in its smallest form; the evolving Smart Home Hub shows patterns working inside one real, growing system.
- **A smoke test proves the machinery, not the logic** — a green smoke test on day one means Maven, JUnit, the compiler, and Surefire are all wired up correctly.
- **`.gitignore` keeps local-only files local** — `CLAUDE.md`, `curriculum.md`, and `progress-tracker.md` are never committed, so `git add .` is always safe.

---

## 8. Challenge (with hidden answer)

**Challenge:** Without peeking, predict what would happen if you *deleted* the `maven-surefire-plugin` block from `katas/pom.xml` and ran `mvn test` again on a very old default Maven setup. Would the build fail? Would the test fail? Would you notice anything at all?

<details>
  <summary>Click to reveal the answer</summary>

The build would very likely still print **`BUILD SUCCESS`** — which is the trap. Without a modern Surefire plugin, an old Maven default may not know how to discover **JUnit 5** tests at all. Instead of failing, it would simply find **zero** tests and report `Tests run: 0`.

That is worse than a failure. A red build shouts at you; a build that quietly runs *no* tests lets you believe you are protected when you are not. This is why our POMs pin `maven-surefire-plugin` 3.x explicitly: it is the piece that actually finds and runs JUnit 5 tests. The lesson generalizes — **"no tests were run" is a bug, not a pass.** Always glance at the `Tests run: N` count, not just the `BUILD SUCCESS` line.

(On a *modern* Maven, the bundled Surefire is new enough to still run JUnit 5, so you might get lucky and see `Tests run: 1`. Pinning the version removes the luck.)
</details>

---

## 9. Catalog update

No catalog entry this lesson — the pattern catalog gets its first entry when we meet our first principle (the Single Responsibility Principle, Lesson 05). This lesson only seeded the empty `pattern-catalog.md` so it's ready.

---

## 10. What's next

**Lesson 01 — Objects, encapsulation, and interfaces.** We write the first real Smart Home code: a `Device` interface and two implementations, and we unpack the single most load-bearing idea in the entire course — programming to a *contract* instead of to a concrete implementation.

---

_Lesson 00 of the curriculum · Phase 0, Chapter 00 · Setup complete._
