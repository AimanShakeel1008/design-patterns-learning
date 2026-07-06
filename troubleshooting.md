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
