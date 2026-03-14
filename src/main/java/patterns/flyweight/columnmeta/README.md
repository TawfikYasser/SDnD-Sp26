# Flyweight: Column Metadata

## The Problem

In the before version, every `DataRowBefore` object creates four new `ColumnMetaBefore` objects in its constructor: one for `id`, one for `name`, one for `department`, one for `salary`. These four objects are identical across every row in the dataset. Every row has the same column names, the same data types, the same nullability flags.

If you process one million rows, you allocate four million `ColumnMetaBefore` objects. They are all the same. None of them carry anything unique to their specific row. They exist purely because the schema was defined inside the row constructor instead of being shared across rows.

The waste is not subtle. Four million objects with identical content, all sitting in memory at the same time, contributing nothing beyond what four objects could provide.

## What Changed

The refactored version separates the schema from the data. `ColumnMetadata` objects are created once through `ColumnMetadataFactory`, which caches them by a key derived from the column name, data type, and nullability. `DatasetSchema` holds an array of these shared `ColumnMetadata` objects. Each `DataRow` holds a reference to the shared schema plus its own unique values.

No matter how many rows you process, the schema objects are created exactly once. The `ColumnMetadataFactory` cache also reuses objects across different schemas. If an `employees` schema and an `audit_log` schema both have an `id INTEGER NOT NULL` column, they share the same `ColumnMetadata` object for that column.

## Before

```java
public class DataRowBefore {
    private final ColumnMetaBefore[] schema; // rebuilt identically on every row
    private final String[] values;

    public DataRowBefore(String[] values) {
        this.schema = new ColumnMetaBefore[]{
                new ColumnMetaBefore("id",         "INTEGER", false),
                new ColumnMetaBefore("name",        "VARCHAR", false),
                new ColumnMetaBefore("department",  "VARCHAR", true),
                new ColumnMetaBefore("salary",      "DECIMAL", false)
        };
        this.values = values;
    }
}
// 1,000,000 rows = 4,000,000 identical ColumnMetaBefore objects
```

## After

```java
// ColumnMetadataFactory.java — creates and caches metadata objects
public class ColumnMetadataFactory {
    private static final Map<String, ColumnMetadata> cache = new HashMap<>();

    public static ColumnMetadata get(String columnName, String dataType, boolean nullable) {
        String key = columnName + ":" + dataType + ":" + nullable;
        if (!cache.containsKey(key)) {
            cache.put(key, new ColumnMetadata(columnName, dataType, nullable));
        }
        return cache.get(key);
    }
}

// DatasetSchema.java — the shared schema object
public class DatasetSchema {
    private final ColumnMetadata[] columns;

    public DatasetSchema(ColumnMetadata[] columns) {
        this.columns = columns;
    }

    public ColumnMetadata getColumn(int index) { return columns[index]; }
    public int columnCount()                   { return columns.length; }
}

// DataRow.java — holds unique values + a reference to the shared schema
public class DataRow {
    private final DatasetSchema schema;  // shared flyweight reference
    private final String[] values;       // unique per row

    public DataRow(DatasetSchema schema, String[] values) {
        this.schema = schema;
        this.values = values;
    }
}
```

Building the schema and processing rows:

```java
DatasetSchema schema = new DatasetSchema(new ColumnMetadata[]{
    ColumnMetadataFactory.get("id",         "INTEGER", false),
    ColumnMetadataFactory.get("name",        "VARCHAR", false),
    ColumnMetadataFactory.get("department",  "VARCHAR", true),
    ColumnMetadataFactory.get("salary",      "DECIMAL", false)
});

// 1,000,000 rows share the same 4 ColumnMetadata objects
for (String[] row : rawData) {
    new DataRow(schema, row).print();
}
```

The factory cache also carries across different schemas. When the `transactions` schema asks for `id INTEGER NOT NULL`, it gets back the same object that was already created for `employees`. The total number of `ColumnMetadata` objects in memory is bounded by the number of distinct column definitions, not by the number of rows or schemas.
