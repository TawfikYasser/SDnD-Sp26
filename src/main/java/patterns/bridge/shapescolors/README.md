# Bridge: Shapes and Colors

## The Concept

This example demonstrates the Bridge pattern at its simplest. Imagine you have shapes like circles and squares, and colors like red and blue, and you want to combine them freely. If you solved this with inheritance alone, you would need a class for each combination: `RedCircle`, `BlueCircle`, `RedSquare`, `BlueSquare`. Add a third color or a third shape and the number of classes multiplies again. With four shapes and four colors, you need sixteen classes.

The Bridge pattern breaks this by separating the two independent dimensions into their own hierarchies and connecting them with composition. A shape holds a reference to a color. You can combine any shape with any color at construction time without needing a dedicated class for each pair.

## The Code

```java
// Color.java — the right side of the bridge (the implementation hierarchy)
public interface Color {
    void applyColor(String shapeName);
}

// Colors.java — concrete color implementations
class Red implements Color {
    @Override
    public void applyColor(String shapeName) {
        System.out.println("[Red]  Painting " + shapeName + " in RED");
    }
}

class Blue implements Color {
    @Override
    public void applyColor(String shapeName) {
        System.out.println("[Blue] Painting " + shapeName + " in BLUE");
    }
}

// Shape.java — the left side of the bridge (the abstraction hierarchy)
public abstract class Shape {
    protected Color color;  // the bridge

    protected Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}

// Shapes.java — concrete shapes that delegate to the color
class Circle extends Shape {
    public Circle(Color color) { super(color); }

    @Override
    public void draw() {
        System.out.print("[Circle] Drawing circle... ");
        color.applyColor("Circle");
    }
}

class Square extends Shape {
    public Square(Color color) { super(color); }

    @Override
    public void draw() {
        System.out.print("[Square] Drawing square... ");
        color.applyColor("Square");
    }
}
```

At the call site, you mix and match freely:

```java
Shape redCircle   = new Circle(new Red());
Shape blueCircle  = new Circle(new Blue());
Shape redSquare   = new Square(new Red());
Shape blueSquare  = new Square(new Blue());

redCircle.draw();
blueSquare.draw();
```

Adding a green color means one new class that implements `Color`. Adding a triangle means one new class that extends `Shape`. Neither change touches the other side of the bridge.
