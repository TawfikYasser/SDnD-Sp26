# Singleton: Pipeline Logger (Initialization-on-Demand Holder)

## The Problem

The `Pipeline` class in the before version uses double-checked locking, which is a valid pattern for thread-safe lazy initialization. But there is a subtle problem with it: the `INSTANCE` field is not `volatile`. Without that keyword, the Java memory model does not guarantee that a thread acquiring the lock will see the fully constructed object. The JVM is allowed to reorder the write to `INSTANCE` before the constructor finishes, so another thread could see a non-null reference pointing to a half-initialized object. This is a well-known Java concurrency hazard that trips up people who know enough to reach for double-checked locking but not enough to know it requires `volatile` to actually be correct.

Beyond the correctness issue, there is also a readability concern. Double-checked locking with a volatile field is not the simplest thing to read, explain, or review. There is a better option that requires less ceremony and has no gotchas.

## What Changed

The refactored version uses the Initialization-on-Demand Holder idiom. Instead of coordinating threads manually with synchronized and volatile, the implementation relies on the JVM's class-loading guarantees. A private static inner class called `Holder` holds the `INSTANCE` field. The JVM only initializes `Holder` when it is first accessed, which happens when `getInstance()` is called for the first time. Class initialization in Java is guaranteed to be thread-safe without requiring any explicit synchronization.

The `logs` list is also upgraded to a `synchronized` list so that concurrent `log()` calls from multiple threads are safe.

## Before

```java
public class Pipeline {

    private Pipeline() {
        logs = new ArrayList<>();
    }

    List<String> logs;

    private static Pipeline INSTANCE;  // should be volatile but is not

    public static Pipeline getInstance() {
        if (INSTANCE == null) {
            synchronized (Pipeline.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Pipeline();
                }
            }
        }
        return INSTANCE;
    }

    public void log(String msg)     { logs.add(msg); }
    public List<String> getLogs()   { return logs; }
}
```

## After

```java
public class PipelineLogger {

    private final List<String> logs;

    private PipelineLogger() {
        logs = Collections.synchronizedList(new ArrayList<>());
        System.out.println("PipelineLogger initialised (Holder idiom)");
    }

    private static class Holder {
        private static final PipelineLogger INSTANCE = new PipelineLogger();
    }

    public static PipelineLogger getInstance() {
        return Holder.INSTANCE;
    }

    public void log(String msg)     { logs.add(msg); }
    public List<String> getLogs()   { return logs; }
}
```

The Holder idiom is thread-safe, lazy, and has no boilerplate. The JVM guarantees that `Holder.INSTANCE` is initialized exactly once, and no explicit synchronization is needed to make that happen.
