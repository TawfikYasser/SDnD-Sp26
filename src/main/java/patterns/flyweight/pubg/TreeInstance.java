package patterns.flyweight.pubg;

// Light Weight object
public class TreeInstance {


    private final TreeModel model;

    private final float x;
    private final float y;

    public TreeInstance(TreeModel model, float x, float y) {
        this.model = model;
        this.x     = x;
        this.y     = y;
    }


    public void draw() {
        model.draw(x, y);
    }

    public String getType() { return model.getType(); }
    public float  getX()    { return x; }
    public float  getY()    { return y; }
}
