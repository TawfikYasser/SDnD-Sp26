# Abstract Factory: Bank and Loan

## The Problem

In the before version, `BankFactory` and `LoanFactory` both extend `AbstractFactory`, which declares both `getBank` and `getLoan`. That means `BankFactory` has to implement `getLoan` even though it has nothing to do with loans, so it just returns `null`. And `LoanFactory` has to implement `getBank` for the same reason, returning `null` there too.

The calling code also needs to know which concrete factory to instantiate upfront. If you want a bank, you write `new BankFactory()`. If you want a loan, you write `new LoanFactory()`. There is no central place that makes this selection decision based on some input. Every caller has to hard-code which factory they want.

## What Changed

The core structure of `AbstractFactory`, `BankFactory`, and `LoanFactory` stays the same, but the refactored version adds `FactoryCreator`, a class that takes a string like `"Bank"` or `"Loan"` and returns the appropriate factory. This is the entry point callers use instead of constructing factories directly.

The real value here is that the client now works entirely through `AbstractFactory` and never needs to mention `BankFactory` or `LoanFactory` by name. The selection logic is in one place, and swapping in a different factory implementation only requires changing that one class.

## Before

```java
// Caller creates factories directly by name
AbstractFactory bankFactory = new BankFactory();
Bank bank = bankFactory.getBank("HDFC");

AbstractFactory loanFactory = new LoanFactory();
Loan loan = loanFactory.getLoan("Home");
loan.getInterestRate(8.5);
loan.calculateLoanPayment(500000, 20);
```

## After

```java
// FactoryCreator.java
public class FactoryCreator {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("Bank")) return new BankFactory();
        if (choice.equalsIgnoreCase("Loan")) return new LoanFactory();
        return null;
    }
}
```

And at the call site, the concrete factory type is never mentioned:

```java
AbstractFactory bankFactory = FactoryCreator.getFactory("Bank");
Bank hdfc  = bankFactory.getBank("HDFC");
Bank icici = bankFactory.getBank("ICICI");
Bank sbi   = bankFactory.getBank("SBI");

AbstractFactory loanFactory = FactoryCreator.getFactory("Loan");
Loan home = loanFactory.getLoan("Home");
home.getInterestRate(8.5);
home.calculateLoanPayment(500000, 20);

Loan car = loanFactory.getLoan("Car");
car.getInterestRate(10.0);
car.calculateLoanPayment(200000, 5);
```

The `FactoryCreator` acts as a second level of indirection. The client works against the abstract factory interface, and `FactoryCreator` owns the decision of which factory to hand back.
