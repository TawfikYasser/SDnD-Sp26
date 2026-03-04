package patterns.singleton.doublechecked.after;

public class DoubleCheckedSingleton {

    private static volatile DoubleCheckedSingleton instance;

    private DoubleCheckedSingleton() {
        System.out.println("DoubleCheckedSingleton created (hashCode: " + hashCode() + ")");
    }

    public static DoubleCheckedSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedSingleton();
                }
            }
        }
        return instance;
    }
}
