package patterns.singleton.printer.before;

public class Printer {

    private Printer() {}

    private static Printer INSTANCE;

    public static Printer getInstance() {
        return INSTANCE;
    }

    public void print(String text) {
        System.out.println("[Printer] " + text);
    }
}
