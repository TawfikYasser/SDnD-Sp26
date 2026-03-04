package patterns.abstractfactory.food.after;

public class Pizza implements Dish {
    @Override public String getName() { return "Pizza"; }
    @Override public void serve() { System.out.println("Serving Pizza, thin crust margherita"); }
}
