package patterns.bridge.shapescolors;


class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }


    @Override
    public void draw() {
        System.out.print("[Circle] Drawing circle... ");
        color.applyColor("Circle");
    }
}


class Square extends Shape {

    public Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.print("[Square] Drawing square... ");
        color.applyColor("Square");
    }
}
