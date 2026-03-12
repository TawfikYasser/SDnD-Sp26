package patterns.flyweight.pubg;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameWorld {

    private final TreeFactory    factory   = new TreeFactory();
    private final List<TreeInstance> instances = new ArrayList<>();


    public void spawn(String type, float x, float y) {
        TreeModel model = factory.getModel(type);  
        instances.add(new TreeInstance(model, x, y));
    }

    public void spawnMany(int count, String... types) {
        Random rnd = new Random(42);
        for (int i = 0; i < count; i++) {
            String type = types[rnd.nextInt(types.length)];
            spawn(type, rnd.nextFloat() * 1000, rnd.nextFloat() * 1000);
        }
    }

    public void renderSample(int n) {
        System.out.println("--- Rendering first " + n + " of "
                + instances.size() + " instances ---");
        instances.stream().limit(n).forEach(TreeInstance::draw);
        System.out.println("--- (" + (instances.size() - n) + " more not shown) ---");
    }


}
