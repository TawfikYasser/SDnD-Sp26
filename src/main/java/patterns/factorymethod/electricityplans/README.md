# Factory Method: Electricity Plans

## The Problem

In the before version, the caller instantiates a plan directly. If you need a domestic plan, you write `new DomesticPlan()`. If you need a commercial one, you write `new CommercialPlan()`. Every part of the code that needs a plan has to know the exact class name and construct it by hand.

This becomes a problem when the type of plan is determined at runtime, for example based on user input, a configuration file, or a database value. At that point, you end up writing string comparisons and `if/else` branches to figure out which class to instantiate, and that logic tends to get duplicated across different parts of the system.

## What Changed

The refactored version introduces `GetPlanFactory`, a class whose only job is to take a string and return the right plan. Calling code asks the factory for a plan by name and works with whatever comes back through the abstract `Plan` type. The construction decision is centralized in one place.

This is the simplest possible Factory Method implementation: a single factory class with one method that encapsulates the branching logic. Instead of `if/else` chains scattered across the codebase, there is one switch expression in one class. Adding a new plan type means adding a new case in that method and a new class, rather than hunting for every place that constructs plans.

## Before

```java
// Caller directly instantiates the concrete type
Plan plan = new DomesticPlan();
plan.getRate();
plan.calculateBill(100);
```

## After

```java
// GetPlanFactory.java
public class GetPlanFactory {

    public Plan getPlan(String type) {
        if (type == null) return null;
        return switch (type.toUpperCase()) {
            case "DOMESTIC"      -> new DomesticPlan();
            case "COMMERCIAL"    -> new CommercialPlan();
            case "INSTITUTIONAL" -> new InstitutionalPlan();
            default              -> null;
        };
    }
}
```

And at the call site:

```java
GetPlanFactory factory = new GetPlanFactory();

Plan domestic = factory.getPlan("DOMESTIC");
if (domestic != null) {
    domestic.getRate();
    domestic.calculateBill(100);
}
```

The caller never references a concrete plan class. It asks the factory for a plan and works with the abstract type. The factory owns the decision about which class to create.
