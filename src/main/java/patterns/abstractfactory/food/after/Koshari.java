package patterns.abstractfactory.food.after;

public class Koshari implements Dish {
    @Override public String getName() { return "koshary"; }
    @Override public void serve() { System.out.println("Serving Koshari (koshary), Egyptian street food"); }
}
