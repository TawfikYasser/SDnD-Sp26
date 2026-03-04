package patterns.abstractfactory.food.before;
public class Shai implements Drink {
    @Override public String getName() { return "shai"; }
    @Override public void pour() { System.out.println("Pouring Mint Tea"); }
}
