package patterns.abstractfactory.food.before;
public class Soda implements Drink {
    @Override public String getName() { return "Soda"; }
    @Override public void pour() { System.out.println("Pouring Soda"); }
}
