# Factory Method: Interview Process

## The Problem

In the before version, when you need a junior candidate and their resume, you instantiate `JuniorCandidate` and `JuniorResume` directly. The same goes for senior, mid-level, and team leader levels. Every place in the code that runs a simulated interview has to know and name the concrete classes for both the candidate and the resume separately.

This means the caller is responsible for keeping them in sync. Nothing enforces that you pair a `JuniorCandidate` with a `JuniorResume` and not accidentally a `SeniorResume`. There is no single place that owns the concept of what constitutes a "junior interview package." The knowledge is just assumed to be implicit and consistent across all callsites.

## What Changed

The refactored version introduces an `Interview` interface with two factory methods: `conductInterview()` returns a `Candidate`, and `createCV()` returns a `Resume`. Each concrete factory like `JuniorInterviewFactory` or `SeniorInterviewFactory` implements both methods, so the correct candidate and resume are always returned together from the same object.

`CandidateSimulator` and `ResumeSimulator` are both given an `Interview` factory and call the appropriate method on it. Neither simulator knows what level it is dealing with. The level is baked into whichever factory you pass in, and swapping the factory at the call site is all you need to run a different tier of simulation.

Adding a new level means creating three new classes: a factory, a candidate, and a resume. Nothing existing needs to change.

## Before

```java
// Caller manually pairs the candidate and resume for each level
Candidate c = new JuniorCandidate();
Resume    r = new JuniorResume();
System.out.println(c.prepareCandidate() + " | " + r.createCV());
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

// CandidateSimulator.java
public class CandidateSimulator {
    private final Interview factory;

    public CandidateSimulator(Interview factory) {
        this.factory = factory;
    }

    public Candidate run() {
        return factory.conductInterview();
    }
}
```

And at the call site, all four levels run through the same loop:

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

The candidate and resume are always guaranteed to match because they come from the same factory. The loop does not need to know anything about specific levels.
