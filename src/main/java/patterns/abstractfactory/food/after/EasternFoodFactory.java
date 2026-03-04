package patterns.abstractfactory.food.after;

public class EasternFoodFactory implements FoodFactory {
    @Override public Dish  createMainDish() { return new Koshari(); }
    @Override public Dish  createSideDish() { return new Fattah(); }
    @Override public Drink createDrink()    { return new Shai(); }
}
