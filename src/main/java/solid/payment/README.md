# Payment System: Interface Segregation & Liskov Substitution Principles

## The Problem

This example actually shows two separate but related issues that tend to appear together in payment systems, and they are worth understanding separately before looking at how they connect.

The first issue is with `FawryProcessor`. It has two methods, `payVisa` and `payApplePay`, hardcoded right on the class. If you want to add a new payment method, you have to open `FawryProcessor` and add another method. The class has no interface, no common contract, and calling code has to know the exact method name for each payment type. This makes the class hard to extend and impossible to use polymorphically.

The second issue is with the `PaymentProcessor` interface in the before and violation versions. That interface declares both `process` and `refund` together. On the surface this looks reasonable, every processor should be able to process and refund. But Meeza does not support refunds. So `MeezaProcessor` implements the interface, throws an `UnsupportedOperationException` from `refund`, and ships. The code compiles. No error until runtime.

This is the Liskov Substitution Principle being broken in a concrete way. If you have a list of `PaymentProcessor` objects and you call `refund` on each one, you expect them all to work because they all said they were `PaymentProcessor`. Meeza lied. It cannot be safely substituted where the contract says it can. Any code that calls `refund` has to either know which specific type it is dealing with or wrap the call in a try/catch and hope for the best.

The root cause is that the interface is too fat. It forced a capability onto Meeza that Meeza does not have.

## What Changed

The refactored version splits the `PaymentProcessor` interface down to just `process`. A separate `Refundable` interface handles the refund contract. Processors that support refunds, like `VisaProcessor` and `ApplePayProcessor`, implement both interfaces. `MeezaProcessor` only implements `PaymentProcessor`. It never touches `Refundable` and never has to throw an exception to satisfy a contract it cannot fulfill.

This also gives you a clean way to write utility methods. The `issueRefund` method in `Main` takes a `Refundable` parameter, not a `PaymentProcessor`. That means only processors that genuinely support refunds can be passed into it. The type system catches the mistake at compile time instead of at runtime in production.

The `FawryProcessor` problem is fixed by introducing the `PaymentProcessor` interface as the common contract. Now any processor just implements `process`, and calling code works against the interface rather than the concrete class. Adding a new processor means writing a new class, not modifying an existing one.

A `PaymentValidator` interface is also introduced to separate card validation from payment processing, since those are different concerns that were getting blended together.

## Before

```java
// PaymentProcessor.java — interface with too many responsibilities
public interface PaymentProcessor {
    boolean process(double amount);
    void refund(double amount);  // Meeza cannot do this
}

// MeezaProcessor.java — forced to implement something it cannot support
public class MeezaProcessor implements PaymentProcessor {
    @Override
    public boolean process(double amount) {
        System.out.println("Pay " + amount + " using Meeza!");
        return true;
    }

    @Override
    public void refund(double amount) {
        throw new UnsupportedOperationException("Meeza does not support refunds!");
    }
}

// FawryProcessor.java — no interface, hardcoded method names per payment type
public class FawryProcessor {
    public boolean payVisa(double amount)     { return true; }
    public boolean payApplePay(double amount) { return true; }
}
```

## After

```java
// PaymentProcessor.java — only what every processor can do
public interface PaymentProcessor {
    boolean process(double amount);
}

// Refundable.java — only for processors that actually support refunds
public interface Refundable {
    void refund(double amount);
}

// MeezaProcessor.java — honest about its capabilities
public class MeezaProcessor implements PaymentProcessor {
    @Override
    public boolean process(double amount) {
        System.out.println("Pay " + amount + " using Meeza!");
        return true;
    }
}

// VisaProcessor.java — implements both because it supports both
public class VisaProcessor implements PaymentProcessor, Refundable {
    @Override
    public boolean process(double amount) {
        System.out.println("Pay " + amount + " using Visa!");
        return true;
    }

    @Override
    public void refund(double amount) {
        System.out.println("Refunding " + amount + " via Visa.");
    }
}

// ApplePayProcessor.java
public class ApplePayProcessor implements PaymentProcessor, Refundable {
    @Override
    public boolean process(double amount) {
        System.out.println("Pay " + amount + " using Apple Pay!");
        return true;
    }

    @Override
    public void refund(double amount) {
        System.out.println("Apple Pay refund procedure...");
    }
}

// PaymentValidator.java — card validation is a separate concern
public interface PaymentValidator {
    boolean isValid(Card card);
}
```

Now any method that needs to issue a refund can simply ask for a `Refundable`. If you try to pass `MeezaProcessor` in, the compiler stops you. No runtime surprises, no defensive try/catch blocks scattered across the codebase. Each interface describes exactly what it promises, nothing more.
