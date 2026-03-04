package patterns.abstractfactory.food.before;
public class Fattah implements Dish {
    @Override public String getName() { return "fattah"; }
    @Override public void serve() { System.out.println("Serving Fattah, rice, bread, meat, sauce"); }
}
