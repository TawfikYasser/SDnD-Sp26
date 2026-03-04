package patterns.abstractfactory.food.before;
public class Burger implements Dish {
    @Override public String getName() { return "Burger"; }
    @Override public void serve() { System.out.println("Serving Burger, grilled beef patty"); }
}
