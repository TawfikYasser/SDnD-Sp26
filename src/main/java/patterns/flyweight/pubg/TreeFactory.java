package patterns.flyweight.pubg;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class TreeFactory {


    private final Map<String, TreeModel> cache = new HashMap<>();

    public TreeModel getModel(String type) {
        if (!cache.containsKey(type)) {
            System.out.println("[TreeFactory] MISS '" + type + "' — creating model");
            cache.put(type, new TreeModel(type));
        } else {
            System.out.println("[TreeFactory] HIT  '" + type + "' — reusing model");
        }
        return cache.get(type);
    }

    public int  cacheSize()      { return cache.size(); }
    public long cachedMemoryMB() {
        return cache.values().stream().mapToLong(TreeModel::getTotalMB).sum();
    }
    public Map<String, TreeModel> getCache() {
        return Collections.unmodifiableMap(cache);
    }
}
