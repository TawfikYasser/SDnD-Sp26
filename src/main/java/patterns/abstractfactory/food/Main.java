package patterns.abstractfactory.food;

import patterns.abstractfactory.food.after.*;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        System.out.println("\n--- eastern ---");
        patterns.abstractfactory.food.before.Koshari koshari = new patterns.abstractfactory.food.before.Koshari();
        patterns.abstractfactory.food.before.Fattah  fattah  = new patterns.abstractfactory.food.before.Fattah();
        patterns.abstractfactory.food.before.Shai    shai    = new patterns.abstractfactory.food.before.Shai();
        koshari.serve();
        fattah.serve();
        shai.pour();

        System.out.println("\n--- western ---");
        patterns.abstractfactory.food.before.Burger burger = new patterns.abstractfactory.food.before.Burger();
        patterns.abstractfactory.food.before.Pizza  pizza  = new patterns.abstractfactory.food.before.Pizza();
        patterns.abstractfactory.food.before.Soda   soda   = new patterns.abstractfactory.food.before.Soda();
        burger.serve();
        pizza.serve();
        soda.pour();

    }

    static void runAfter() {

        System.out.println("\n--- eastern ---");
        Restaurant eastern = new Restaurant(new EasternFoodFactory());
        eastern.serveMeal();

        System.out.println("\n--- western ---");
        Restaurant western = new Restaurant(new WesternFoodFactory());
        western.serveMeal();

    }
}
