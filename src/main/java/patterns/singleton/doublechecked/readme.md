# Singleton: Double-Checked Locking
 
## The Problem
 
The `UnsafeSingleton` class uses a simple null check before creating the instance. In a single-threaded program, this works fine. The problem shows up when multiple threads call `getInstance()` at the same time.
 
If two threads both hit the `if (instance == null)` check before either of them has finished creating the object, both of them will see `null` and both will proceed to call `new UnsafeSingleton()`. You end up with two instances. Now you no longer have a singleton, which defeats the whole point.
 
This is a race condition, and it is not hypothetical. Under any reasonable load it will happen. In a real application where the singleton manages something like a database connection pool or a shared resource, having two of them in flight simultaneously can cause silent data corruption or resource leaks that are very hard to trace.
 
## What Changed
 
The fix adds `volatile` to the field declaration and uses double-checked locking inside `getInstance()`. The outer null check is an optimization: once the instance exists, threads skip the synchronized block entirely and just return the field directly. The inner null check is the safety net: even if two threads both pass the outer check simultaneously, only one of them can hold the lock at a time, so only one will create the instance.
 
The `volatile` keyword is essential here. Without it, the JVM or CPU can reorder operations such that a thread sees a non-null reference to a partially constructed object. `volatile` prevents that reordering and ensures any thread that reads the field sees the fully initialized instance.
 
## Before
 
```java
public class UnsafeSingleton {
 
    private static UnsafeSingleton instance;  // not volatile
 
    private UnsafeSingleton() {
        System.out.println("UnsafeSingleton created (hashCode: " + hashCode() + ")");
    }
 
    public static UnsafeSingleton getInstance() {
        if (instance == null) {           // two threads can both pass this
            instance = new UnsafeSingleton();
        }
        return instance;
    }
}
```
 
## After
 
```java
public class DoubleCheckedSingleton {
 
    private static volatile DoubleCheckedSingleton instance;  // volatile is required
 
    private DoubleCheckedSingleton() {
        System.out.println("DoubleCheckedSingleton created (hashCode: " + hashCode() + ")");
    }
 
    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {                            // fast path (no lock)
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {                    // safe path (with lock)
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
```
 
With this in place, no matter how many threads call `getInstance()` simultaneously, only one instance is ever created, and every thread that reads the result is guaranteed to see the fully constructed object.
