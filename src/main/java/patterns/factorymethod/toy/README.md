# Factory Method: Toy Factory

## The Problem

In the before version, the caller is responsible for deciding which concrete toy class to instantiate. If you want a car toy, you write `new CarToy()`. If you want a doll, you write `new DollToy()`. This is fine for a small program, but it means the calling code is tightly coupled to specific implementations. Every place in the codebase that creates a toy knows the exact class name and constructs it directly.

When you want to add a new toy type, you have to find all the places in the codebase that create toys and update them. There is no central place that owns the creation decision. There is also no way to add shared logic around the creation step, like setup, validation, or post-processing, without duplicating it everywhere.

## What Changed

The refactored version introduces an abstract `ToyFactory` class with a `createToy()` method that subclasses override. `CarFactory` returns a `CarToy`, `DollFactory` returns a `DollToy`. The `ToyFactory` base class also provides an `orderToy()` method that calls `createToy()` and then calls `play()` on the result.

The key idea is that `orderToy()` does not know or care what kind of toy gets created. It just calls `createToy()` and works with whatever comes back through the `Toy` interface. The concrete decision about which class to instantiate is pushed down into each specific factory subclass, not scattered across the codebase.

Adding a new toy type now means writing a new `Toy` implementation and a new `ToyFactory` subclass, without touching any existing code.

## Before

```java
// Caller creates concrete types directly
CarToy  car  = new CarToy();
DollToy doll = new DollToy();
car.play();
doll.play();
```

## After

```java
// ToyFactory.java
public abstract class ToyFactory {
    abstract Toy createToy();  // subclass decides the type

    public void orderToy() {
        Toy toy = createToy();
        toy.play();
    }
}

// CarFactory.java
public class CarFactory extends ToyFactory {
    @Override
    Toy createToy() {
        return new CarToy();
    }
}

// DollFactory.java
public class DollFactory extends ToyFactory {
    @Override
    Toy createToy() {
        return new DollToy();
    }
}
```

And at the call site:

```java
ToyFactory carFactory  = new CarFactory();
carFactory.orderToy();

ToyFactory dollFactory = new DollFactory();
dollFactory.orderToy();
```

The caller works entirely through `ToyFactory`. It does not know or need to know what `createToy()` returns under the hood.
