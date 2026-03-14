# Abstract Factory: Interview Levels

## The Problem

In the before version, if you want to run a junior interview simulation, you instantiate `JuniorCandidate` and `JuniorResume` directly. If you want to run a senior one, you replace both classes. The calling code is responsible for knowing which candidate pairs with which resume for each level, and for keeping that pairing consistent everywhere it appears.

This works fine when there are only two levels but starts to fall apart as the number grows. Adding a mid-level or team-leader tier means updating every place that constructs candidates and resumes. If the caller forgets to update both, you might end up simulating a junior candidate paired with a senior resume, and the code gives you no warning at all.

## What Changed

The refactored version introduces an `Interview` interface that groups the two factory methods together: `conductInterview()` returns a `Candidate` and `createCV()` returns a `Resume`. Each concrete factory like `JuniorInterviewFactory`, `MidLevelInterviewFactory`, `SeniorInterviewFactory`, and `TeamLeaderInterviewFactory` implements both methods, so the correct pair is always returned from the same object.

`CandidateSimulator` and `ResumeSimulator` each take an `Interview` factory and delegate to it. The simulators do not know what level they are running; they just know how to ask the factory for what they need.

At the call site, all four levels are driven through the same loop by iterating over an array of factories. Adding a new level is one new factory class, one new candidate class, and one new resume class. No existing code changes.

## Before

```java
// Caller manually pairs the two classes for each level
Candidate c = new JuniorCandidate();
Resume    r = new JuniorResume();
System.out.println(c.prepareCandidate() + " | " + r.createCV());
// Adding more levels means more manual instantiation here
```

## After

```java
// Interview.java
public interface Interview {
    Candidate conductInterview();
    Resume    createCV();
}

// JuniorInterviewFactory.java
public class JuniorInterviewFactory implements Interview {
    @Override public Candidate conductInterview() { return new JuniorCandidate(); }
    @Override public Resume    createCV()          { return new JuniorResume(); }
}

// MidLevelInterviewFactory.java
public class MidLevelInterviewFactory implements Interview {
    @Override public Candidate conductInterview() { return new MidLevelCandidate(); }
    @Override public Resume    createCV()          { return new MidLevelResume(); }
}

// CandidateSimulator.java
public class CandidateSimulator {
    private final Interview factory;
    public CandidateSimulator(Interview factory) { this.factory = factory; }
    public Candidate run() { return factory.conductInterview(); }
}
```

And the call site runs every level the same way:

```java
Interview[] factories = {
    new JuniorInterviewFactory(),
    new MidLevelInterviewFactory(),
    new SeniorInterviewFactory(),
    new TeamLeaderInterviewFactory()
};

for (Interview factory : factories) {
    CandidateSimulator cs = new CandidateSimulator(factory);
    ResumeSimulator    rs = new ResumeSimulator(factory);
    System.out.println(cs.run().prepareCandidate() + " | " + rs.run().createCV());
}
```

The factory guarantees that the candidate and resume always come from the same tier. The loop does not need to know which tier it is running.
