package patterns.factorymethod.toy.after;

public class DollFactory extends ToyFactory {
    @Override
    Toy createToy() {
        return new DollToy();
    }
}
