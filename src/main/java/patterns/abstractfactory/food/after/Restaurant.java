package patterns.abstractfactory.food.after;

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
