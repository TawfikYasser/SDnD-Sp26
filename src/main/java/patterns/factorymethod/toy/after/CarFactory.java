package patterns.factorymethod.toy.after;

public class CarFactory extends ToyFactory {
    @Override
    Toy createToy() {
        return new CarToy();
    }
}
