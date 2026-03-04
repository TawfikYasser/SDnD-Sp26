package patterns.abstractfactory.food.after;

public class Shai implements Drink {
    @Override public String getName() { return "shai bil na3na3"; }
    @Override public void pour() { System.out.println("Pouring Mint Tea (shai bil na3na3)"); }
}
