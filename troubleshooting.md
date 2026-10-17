# Troubleshooting — Errors You May Hit, and Their Fixes

This page decodes the errors that actually come up while working through the course. Each entry names the symptom, explains *why* it happens in plain language, and gives the fix. New entries are added whenever a lesson introduces something error-prone.

---

## Setup (Lesson 00)

### `java` or `mvn` is "not recognized" / "command not found"

**Symptom:** the terminal says `'java' is not recognized as an internal or external command` (Windows) or `command not found: mvn`.

**Why:** the tool is either not installed, or installed but not on your system **PATH** — the list of folders your terminal searches for programs.

**Fix:**
1. Confirm it is installed. On Windows, check `where java` and `where mvn`.
2. If installed but not found, add its `bin` folder to your PATH environment variable, then open a **new** terminal (PATH changes only apply to newly opened terminals).
3. Re-check with `java -version` and `mvn -version`.

---

### `mvn -version` shows the wrong Java version

**Symptom:** `mvn -version` prints a Java version that is not 21 (for example 17 or 8).

**Why:** Maven uses whatever the `JAVA_HOME` environment variable points to (or the first `java` on your PATH). You may have several JDKs installed.

**Fix:** set `JAVA_HOME` to your JDK 21 installation folder, open a new terminal, and re-run `mvn -version`. The "Java version" line it prints should now read 21.

---

### First `mvn test` is very slow or seems stuck

**Symptom:** the very first `mvn test` takes a long time and prints many `Downloading...` lines.

**Why:** this is normal and expected **once**. Maven is downloading the plugins and the JUnit library into a local cache (the `.m2` folder in your home directory). Later runs reuse the cache and are fast.

**Fix:** let it finish. It needs an internet connection the first time. Subsequent runs are quick and work offline.

---

### `BUILD FAILURE` mentioning `release version 21 not supported`

**Symptom:** the build fails with a message about release version 21 not being supported.

**Why:** Maven is compiling with an older JDK than 21. The POM asks for Java 21 (`<maven.compiler.release>21</maven.compiler.release>`), but the JDK actually running Maven is older.

**Fix:** install JDK 21 and point `JAVA_HOME` at it (see the "wrong Java version" entry above).

---

### Tests compile but "no tests were run"

**Symptom:** `mvn test` succeeds but reports `Tests run: 0`.

**Why:** usually the test class or its `@Test` methods are not where Maven expects, or an old Surefire plugin cannot see JUnit 5 tests.

**Fix:** confirm test files live under `src/test/java`, mirror the same package as the code, and that the POM pins a modern `maven-surefire-plugin` (3.x). This course's POMs already do.

---

### `there is no POM in this directory`

**Symptom:** `[ERROR] The goal you specified requires a project to execute but there is no POM in this directory`.

**Why:** Maven only reads the `pom.xml` in the folder you are standing in. The repository root has none; each project (`katas/`, `smarthome/`) has its own.

**Fix:** `cd katas` (or `cd smarthome`) first, then run the Maven command.

---

### `-Dexec.mainClass=...` fails in PowerShell (`Unknown lifecycle phase ".mainClass=..."`)

**Symptom:** on Windows PowerShell, `mvn compile exec:java -Dexec.mainClass=com.katas.x.Main` fails with an "Unknown lifecycle phase" error naming `.mainClass=...`.

**Why:** PowerShell splits the unquoted argument at the dot, so Maven receives two broken pieces instead of one setting.

**Fix:** wrap the whole option in double quotes: `mvn compile exec:java "-Dexec.mainClass=com.katas.x.Main"`. The quotes are harmless in other shells, so the course always writes them.

---

### Maven cannot resolve `exec-maven-plugin:3.6.1`

**Symptom:** `mvn compile exec:java` fails with `Plugin org.codehaus.mojo:exec-maven-plugin:3.6.1 ... could not be resolved`.

**Why:** that version was pinned at the course restart and is flagged to verify; your Maven may not find it (no internet on first use, or a version mismatch).

**Fix:** check your internet connection for the first download. If it still fails, tell me the exact error and we will pin a version your Maven can resolve (`mvn test` does not need this plugin, so tests are unaffected).

---

## Objects and interfaces (Lesson 01)

### `error: balance has private access in ...BankAccount`

**Why:** code outside the class tried to read or write a `private` field. That is encapsulation working, not a bug.

**Fix:** go through the class's public methods instead (`getBalance()`, `withdraw(...)`). Never "fix" it by making the field public.

### `error: Lamp is not abstract and does not override abstract method describe() in Switchable`

**Why:** a class says `implements Switchable` but is missing (or misspelled) one of the promised methods.

**Fix:** add the missing method with exactly the interface's name and parameters, marked `@Override` so the compiler checks the spelling.

---

## Inheritance (Lesson 02)

### `error: constructor Animal in class Animal cannot be applied to given types`

**Why:** a subclass constructor did not call `super(...)` with the arguments its parent's constructor needs. Without an explicit call, Java tries `super()` with no arguments, and the parent has no such constructor.

**Fix:** make `super(name)` (with the right arguments) the first line of the subclass constructor.

### `error: Animal is abstract; cannot be instantiated`

**Why:** `new` was used on an abstract class. Abstract classes exist only to be extended.

**Fix:** build a concrete subclass instead (`new Dog("Rex")`), typed as the parent if you like: `Animal a = new Dog("Rex")`.

### `error: method does not override or implement a method from a supertype`

**Why:** a method marked `@Override` does not match any inherited method — usually a misspelling (`tostring`) or different parameter types. The annotation caught a bug that would otherwise compile silently.

**Fix:** match the parent's method name and parameter types exactly.

### Printing an object shows `ClassName@1b6d3586`

**Why:** the class has no working `toString()` override, so `Object`'s default runs — often because the override was misspelled without `@Override`.

**Fix:** add `@Override public String toString()` and check the spelling.

---

## Composition (Lesson 03)

### `java.lang.UnsupportedOperationException` when adding to a list

**Why:** the list is unmodifiable — made by `List.of(...)` or `List.copyOf(...)` (e.g. `ActivityLog.entries()` returns one on purpose, so history cannot be rewritten).

**Fix:** change data through the owning object's methods (`log.record(...)`). If you genuinely need your own editable copy, make one: `new ArrayList<>(log.entries())`.

### `FragileBaseClassTest` fails with `expected: <6> but was: <3>`

**Why:** `naive/Playlist.addAll` was edited back to `songs.add(song)` (the Try-it experiment in Lesson 03). The test pins the post-tidy-up bug on purpose.

**Fix:** restore `add(song);` inside `naive/Playlist.addAll`. The flip itself is the lesson: the child's behaviour changed although only the parent was edited.

---

## Coupling and UML (Lesson 04)

### `error: OrderLine(MenuItem,int) is not public in OrderLine; cannot be accessed from outside package`

**Why:** `OrderLine`'s constructor is package-private on purpose (no access keyword), so only classes in `com.katas.coupling` — in practice `Order` — can create lines. That is how the composition ("Order owns its lines") is enforced.

**Fix:** create lines through `order.add(item, quantity)` instead of `new OrderLine(...)`.

---

## Contract tests (Lesson 07)

### Fewer tests run than predicted, and a `*ContractTest` subclass shows `Tests run: 0`

**Why:** JUnit runs a contract test's methods only through its concrete subclasses. If the abstract class's `@Test` methods are not visible to a subclass (for example package-private methods inherited by a subclass in a different package), they may not be discovered.

**Fix:** keep the test methods in `DeviceContractTest` `public` (as written), keep the class `abstract`, and make sure each subclass implements `createDevice(...)`. Expect 5 tests per subclass.

### `error: ... is not abstract and does not override abstract method createDevice(String)`

**Why:** a new `*ContractTest` subclass forgot to supply the device to test.

**Fix:** add `@Override protected Device createDevice(String name) { return new YourDevice(name); }`.

---

## Interface segregation (Lesson 08)

### `error: cannot find symbol ... class AbstractDevice` (or `AbstractDeviceTest`)

**Why:** Lesson 08 renamed `AbstractDevice` to `SwitchableDevice` (and its test to `SwitchableDeviceTest`), because not every device switches any more. An old copy, a stale IDE cache, or your own experiment still refers to the old name.

**Fix:** use `SwitchableDevice`. If the error names a file you did not write, delete stale build output with `mvn clean test` from `smarthome/`.

### `error: cannot find symbol ... method turnOn()` on a `Device`

**Why:** since Lesson 08, `Device` only promises `getName()` and `status()`. Switching lives in `Switchable`.

**Fix:** type the variable as `Switchable` (e.g. `Switchable light = new Light("x");`).

### `incompatible types: ... List<Device> cannot be converted to List<Switchable>`

**Why:** routines take `List<Switchable>`, and a list of plain devices may contain things that cannot switch (a smoke alarm, a lock). The compiler is protecting the routine.

**Fix:** pass the switchable devices (`hub.switchables()`, or a `List.of(...)` of switchable objects).

---

## Dependency inversion (Lesson 09)

### `error: package com.smarthome.home does not contain HomeSetup` (or `cannot find symbol ... HomeSetup`)

**Why:** Lesson 09 moved `HomeSetup` (and `HomeSetupTest`) from `com.smarthome.home` to `com.smarthome`, the composition root beside `Main`. An old import or an old copy of the file still uses the former location.

**Fix:** import `com.smarthome.HomeSetup` (or none at all inside `com.smarthome`). Make sure no stale `home/HomeSetup.java` remains, then run `mvn clean test` from `smarthome/`.

### The climate line never appears, or says "no change" unexpectedly

**Why:** `ClimateControl.regulate()` reads the simulated sensor once. Within the 18–24°C range (edges included) it does nothing by design.

**Fix:** check the reading passed to `new SimulatedThermometer("Hall", …)` in `Main`.

---

## Lesson pages

### A lesson page opens with no colours or layout (plain black text)

**Why:** the page could not find its shared stylesheet, `lessons/assets/lesson.css`. That happens when a page is moved or copied out of the `lessons/` folder, since every page links the stylesheet by a relative path.

**Fix:** open pages from inside the cloned repo — start at `lessons/index.html` and follow its cards. Do not move individual `.html` files.

### A diagram's text overlaps its box, or a page looks off in dark mode

**Why:** diagrams are hand-drawn SVG; how they render is something only your browser can confirm.

**Fix:** tell me the page, the figure, and the theme (light or dark) — I will nudge the layout.
