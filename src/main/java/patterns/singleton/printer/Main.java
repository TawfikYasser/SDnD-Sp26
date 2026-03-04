package patterns.singleton.printer;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        patterns.singleton.printer.before.Printer p =
                patterns.singleton.printer.before.Printer.getInstance();

        System.out.println("getInstance() returned: " + p);

        try {
            if (p == null) throw new NullPointerException("getInstance() returned null");
            p.print("hello");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: " + e.getMessage());
        }
    }

    static void runAfter() {

        patterns.singleton.printer.after.Printer p1 =
                patterns.singleton.printer.after.Printer.getInstance();
        patterns.singleton.printer.after.Printer p2 =
                patterns.singleton.printer.after.Printer.getInstance();

        p1.print("hello from p1");
        p2.print("hello from p2");

        System.out.println("p1 == p2 (same instance): " + (p1 == p2));
    }
}
