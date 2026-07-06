// Declares the package (folder-path namespace) this class lives in. The folders
// on disk (com/katas) must match this line exactly, or Java will not find it.
package com.katas;

// A public class is one visible to code in other packages. The file name
// (Main.java) must match the class name (Main) exactly in Java.
public class Main {

    // main is the special method the Java runtime calls first when the program
    // starts. Its signature MUST be exactly this for the JVM to recognise it:
    // public (callable from outside), static (no object needed to call it),
    // void (returns nothing), named main, taking an array of String arguments.
    public static void main(String[] args) {
        // Print one line to the console so we can SEE the toolchain works:
        // if this text appears, Java compiled and ran our code successfully.
        System.out.println("Katas project is ready.");
    }
}
