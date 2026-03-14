# Abstract Factory: Food Restaurant

## The Problem

In the before version, when you want to serve an eastern meal, you explicitly create a `Koshari`, a `Fattah`, and a `Shai` and call the appropriate methods on each. Same story for a western meal: you create a `Burger`, a `Pizza`, and a `Soda`. The calling code has to know and reference each concrete food class by name.

The problem is that the calling code is responsible for assembling a consistent meal from individually named parts. There is nothing stopping you from mixing a `Koshari` with a `Soda`, or serving a `Burger` with `Shai`. Whether that combination makes any sense is left entirely up to whoever is writing the call. The concept of "a complete meal from a particular cuisine" does not exist anywhere in the code as an actual abstraction.

## What Changed

The refactored version introduces a `FoodFactory` interface with three methods: `createMainDish()`, `createSideDish()`, and `createDrink()`. `EasternFoodFactory` implements this to return koshari, fattah, and shai. `WesternFoodFactory` returns burger, pizza, and soda.

The `Restaurant` class takes a `FoodFactory` in its constructor and calls `serveMeal()`, which asks the factory for all three components and serves them. The restaurant does not know which cuisine it is serving. It just knows that a meal has three components and that the factory will return a coherent set.

Switching from eastern to western is just a matter of passing a different factory. Adding a new cuisine means writing a new factory that implements `FoodFactory`, with no changes to `Restaurant`.

## Before

```java
// Eastern meal — caller assembles by hand
Koshari koshari = new Koshari();
Fattah  fattah  = new Fattah();
Shai    shai    = new Shai();
koshari.serve();
fattah.serve();
shai.pour();

// Western meal — same caller, different concrete types
Burger burger = new Burger();
Pizza  pizza  = new Pizza();
Soda   soda   = new Soda();
burger.serve();
pizza.serve();
soda.pour();
```

## After

```java
// FoodFactory.java
public interface FoodFactory {
    Dish  createMainDish();
    Dish  createSideDish();
    Drink createDrink();
}

// EasternFoodFactory.java
public class EasternFoodFactory implements FoodFactory {
    @Override public Dish  createMainDish() { return new Koshari(); }
    @Override public Dish  createSideDish() { return new Fattah(); }
    @Override public Drink createDrink()    { return new Shai(); }
}

// WesternFoodFactory.java
public class WesternFoodFactory implements FoodFactory {
    @Override public Dish  createMainDish() { return new Burger(); }
    @Override public Dish  createSideDish() { return new Pizza(); }
    @Override public Drink createDrink()    { return new Soda(); }
}

// Restaurant.java
public class Restaurant {
    private final FoodFactory factory;

    public Restaurant(FoodFactory factory) {
        this.factory = factory;
    }

    public void serveMeal() {
        Dish  main  = factory.createMainDish();
        Dish  side  = factory.createSideDish();
        Drink drink = factory.createDrink();
        main.serve();
        side.serve();
        drink.pour();
    }
}
```

And at the call site, switching between cuisines is a single constructor argument:

```java
new Restaurant(new EasternFoodFactory()).serveMeal();
new Restaurant(new WesternFoodFactory()).serveMeal();
```
