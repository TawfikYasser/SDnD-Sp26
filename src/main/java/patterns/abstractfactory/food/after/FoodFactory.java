package patterns.abstractfactory.food.after;

public interface FoodFactory {
    Dish  createMainDish();
    Dish  createSideDish();
    Drink createDrink();
}
