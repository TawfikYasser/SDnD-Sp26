package patterns.flyweight.after;

import java.util.HashMap;
import java.util.Map;

public class ColumnMetadataFactory {
    private static final Map<String, ColumnMetadata> cache = new HashMap<>();

    public static ColumnMetadata get(String columnName, String dataType, boolean nullable) {
        String key = columnName + ":" + dataType + ":" + nullable;
        if (!cache.containsKey(key)) {
            cache.put(key, new ColumnMetadata(columnName, dataType, nullable));
        }
        return cache.get(key);
    }

    public static int getCacheSize() { return cache.size(); }

}
