package patterns.bridge.shapescolors;


public class Main {

    public static void main(String[] args) {

        // --- Red Circle ---
        System.out.println("=== Red Circle ===");
        Shape redCircle = new Circle(new Red());
        redCircle.draw();

        // --- Blue Circle ---
        System.out.println("\n=== Blue Circle ===");
        Shape blueCircle = new Circle(new Blue());
        blueCircle.draw();

        // --- Red Square ---
        System.out.println("\n=== Red Square ===");
        Shape redSquare = new Square(new Red());
        redSquare.draw();

        // --- Blue Square ---
        System.out.println("\n=== Blue Square ===");
        Shape blueSquare = new Square(new Blue());
        blueSquare.draw();

        // --- Runtime color swap ---
        // Same shape type, different color injected at construction.
        // Zero changes to Circle or Square.
        System.out.println("\n=== Runtime color swap ===");
        Color[] colors = { new Red(), new Blue() };
        for (Color color : colors) {
            Shape circle = new Circle(color);
            Shape square = new Square(color);
            circle.draw();
            square.draw();
        }
    }
}
