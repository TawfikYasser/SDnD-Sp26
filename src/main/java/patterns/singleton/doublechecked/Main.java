package patterns.singleton.doublechecked;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        runBefore();
        runAfter();
    }

    static void runBefore() throws InterruptedException {

        Runnable task =() -> {
            patterns.singleton.doublechecked.before.UnsafeSingleton s =
                    patterns.singleton.doublechecked.before.UnsafeSingleton.getInstance();
            System.out.println(Thread.currentThread().getName()
                    + " hashcode: " + s.hashCode());
        };

        Thread t1 = new Thread(task, "T-A");
        Thread t2 = new Thread(task, "T-B");
        Thread t3 = new Thread(task, "T-C");
        t1.start(); t2.start(); t3.start();

        t1.join();  t2.join();  t3.join();

    }

    static void runAfter() throws InterruptedException {

        Runnable task =() -> {
            patterns.singleton.doublechecked.after.DoubleCheckedSingleton s =
                    patterns.singleton.doublechecked.after.DoubleCheckedSingleton.getInstance();
            System.out.println(Thread.currentThread().getName()
                    + " hashcode: " + s.hashCode());
        };

        Thread t1 = new Thread(task, "T-A");
        Thread t2 = new Thread(task, "T-B");
        Thread t3 = new Thread(task, "T-C");
        t1.start(); t2.start(); t3.start();
        t1.join();  t2.join();  t3.join();

    }
}
