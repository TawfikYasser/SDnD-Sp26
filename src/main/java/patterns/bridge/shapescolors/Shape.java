package patterns.bridge.shapescolors;


public abstract class Shape {

    protected Color color;

    protected Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}
