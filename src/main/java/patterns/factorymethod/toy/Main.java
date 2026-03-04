package patterns.factorymethod.toy;

import patterns.factorymethod.toy.after.CarFactory;
import patterns.factorymethod.toy.after.DollFactory;
import patterns.factorymethod.toy.after.ToyFactory;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        patterns.factorymethod.toy.before.CarToy  car  = new patterns.factorymethod.toy.before.CarToy();
        patterns.factorymethod.toy.before.DollToy doll = new patterns.factorymethod.toy.before.DollToy();
        car.play();
        doll.play();

    }

    static void runAfter() {

        ToyFactory carFactory = new CarFactory();
        carFactory.orderToy();

        ToyFactory dollFactory = new DollFactory();
        dollFactory.orderToy();

    }
}
