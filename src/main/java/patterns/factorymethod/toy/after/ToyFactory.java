package patterns.factorymethod.toy.after;

public abstract class ToyFactory {

    abstract Toy createToy();

    public void orderToy() {
        Toy toy = createToy();
        toy.play();
    }
}
