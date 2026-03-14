# Flyweight: PUBG Tree Rendering

## The Concept

In a game world like PUBG, the map is covered in thousands of trees. A single tree model includes a mesh and a texture, which together take several megabytes of memory. If you create a new `TreeModel` object for every single tree instance on the map, and there are ten thousand trees, you are loading the same mesh and texture data ten thousand times. The memory cost is enormous and completely unnecessary.

The Flyweight pattern separates the data that is shared across all instances of the same type from the data that is unique to each individual instance. The shared data, which is the mesh and texture in this case, is loaded once and reused everywhere. The unique data, which is just the position on the map, is stored in a lightweight wrapper.

## The Code

```java
// TreeModel.java — the flyweight (shared, heavy object)
public final class TreeModel {
    private final String type;
    private final byte[] mesh;    // 2 MB per model
    private final byte[] texture; // 4 MB per model

    TreeModel(String type) {
        this.type    = type;
        this.mesh    = loadMesh(type);
        this.texture = loadTexture(type);
    }

    public void draw(float x, float y) {
        System.out.printf("[TreeModel.draw] type=%-8s at (%6.1f, %6.1f)%n", type, x, y);
    }
}

// TreeInstance.java — the lightweight wrapper (unique per tree)
public class TreeInstance {
    private final TreeModel model;  // shared reference
    private final float x;          // unique position
    private final float y;

    public TreeInstance(TreeModel model, float x, float y) {
        this.model = model;
        this.x     = x;
        this.y     = y;
    }

    public void draw() { model.draw(x, y); }
}

// TreeFactory.java — the cache that enforces sharing
public class TreeFactory {
    private final Map<String, TreeModel> cache = new HashMap<>();

    public TreeModel getModel(String type) {
        if (!cache.containsKey(type)) {
            cache.put(type, new TreeModel(type));
        }
        return cache.get(type);
    }
}
```

The `GameWorld` calls `factory.getModel(type)` for each tree it spawns. If the model already exists in the cache, the factory returns the same object. If not, it creates one and caches it. Ten thousand oak trees share one `TreeModel` object. The memory cost is six megabytes for the shared model plus a few bytes per instance for the position coordinates, instead of sixty gigabytes if you had allocated a model per instance.
