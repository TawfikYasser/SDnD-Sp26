package patterns.bridge.shapescolors;

// Paitns shape with Red
class Red implements Color {

    @Override
    public void applyColor(String shapeName) {
        System.out.println("[Red]  Painting " + shapeName + " in RED");
    }
}

// Paints shape with blue
class Blue implements Color {

    @Override
    public void applyColor(String shapeName) {
        System.out.println("[Blue] Painting " + shapeName + " in BLUE");
    }
}
