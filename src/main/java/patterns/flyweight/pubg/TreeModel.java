package patterns.flyweight.pubg;

// Flyweight
public final class TreeModel {

    private final String type;
    private final byte[] mesh;    
    private final byte[] texture;


    TreeModel(String type) {
        this.type    = type;
        this.mesh    = loadMesh(type);
        this.texture = loadTexture(type);
        System.out.printf("[TreeModel] Loaded '%s': %d MB (mesh %d MB + texture %d MB)%n",
                type, getTotalMB(), getMeshMB(), getTextureMB());
    }

    public void draw(float x, float y) {
        System.out.printf("[TreeModel.draw] type=%-8s at (%6.1f, %6.1f)"
                + " [shared mesh: %d bytes]%n",
                type, x, y, mesh.length);
    }

    public String getType()    { return type; }
    public int    getMeshMB()  { return mesh.length / 1_000_000; }
    public int    getTextureMB(){ return texture.length / 1_000_000; }
    public int    getTotalMB() { return (mesh.length + texture.length) / 1_000_000; }

    private byte[] loadMesh(String type)    { return new byte[2_000_000]; }
    private byte[] loadTexture(String type) { return new byte[4_000_000]; }
}
