package patterns.abstractfactory.food.after;

public class Fattah implements Dish {
    @Override public String getName() { return "fattah"; }
    @Override public void serve() { System.out.println("Serving Fattah (fattah), rice, bread, meat, sauce"); }
}
