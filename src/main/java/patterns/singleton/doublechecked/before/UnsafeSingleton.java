package patterns.singleton.doublechecked.before;

public class UnsafeSingleton {

    private static UnsafeSingleton instance;

    private UnsafeSingleton() {

        System.out.println("UnsafeSingleton created (hashCode: " + hashCode() + ")");
    }

    public static UnsafeSingleton getInstance() {
        if (instance == null) {
            instance = new UnsafeSingleton();
        }
        return instance;
    }
}
