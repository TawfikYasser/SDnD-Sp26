package patterns.abstractfactory.food.after;

public class WesternFoodFactory implements FoodFactory {
    @Override public Dish  createMainDish() { return new Burger(); }
    @Override public Dish  createSideDish() { return new Pizza(); }
    @Override public Drink createDrink()    { return new Soda(); }
}
