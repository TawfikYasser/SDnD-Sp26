package patterns.abstractfactory.food.before;
public class Koshari implements Dish {
    @Override public String getName() { return "koshary"; }
    @Override public void serve() { System.out.println("Serving Koshari, Egyptian street food"); }
}
