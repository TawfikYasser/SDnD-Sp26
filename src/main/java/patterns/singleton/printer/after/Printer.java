package patterns.singleton.printer.after;

public class Printer {

    private Printer() {}

    private static final Printer INSTANCE = new Printer();

    public static Printer getInstance() {
        return INSTANCE;
    }

    public void print(String text) {
        System.out.println("[Printer] " + text);
    }
}
