package patterns.flyweight;

import patterns.flyweight.after.ColumnMetadata;
import patterns.flyweight.after.ColumnMetadataFactory;
import patterns.flyweight.after.DataRow;
import patterns.flyweight.after.DatasetSchema;
import patterns.flyweight.before.DataRowBefore;

public class Main {
    public static void main(String[] args) {
        runBefore();
        System.out.println("Flyweight");
        runAfter();
    }

    private static void runAfter() {

        System.out.println("Building employees schema:");
        DatasetSchema schema = new DatasetSchema(new ColumnMetadata[]{
                ColumnMetadataFactory.get("id",         "INTEGER", false),
                ColumnMetadataFactory.get("name",        "VARCHAR", false),
                ColumnMetadataFactory.get("department",  "VARCHAR", true),
                ColumnMetadataFactory.get("salary",      "DECIMAL", false)
        });

        System.out.println("\nExporting 5 rows:");
        String[][] rawData = {
                {"1", "Alice",  "Engineering", "95000"},
                {"2", "Bob",    "Marketing",   "72000"},
                {"3", "Carol",  "Engineering", "98000"},
                {"4", "David",  "HR",          "65000"},
                {"5", "Eve",    "Engineering", "102000"},
        };

        for (String[] row : rawData) {
            new DataRow(schema, row).print();
        }

        System.out.println("\nColumnMetadata objects in memory: "
                + ColumnMetadataFactory.getCacheSize()
                + " (not " + (rawData.length * 4) + ")");

        System.out.println("Cache size after employees schema: "
                + ColumnMetadataFactory.getCacheSize());

        System.out.println("\n=== Schema: transactions ===");
        DatasetSchema transactionsSchema = new DatasetSchema(new ColumnMetadata[]{
                ColumnMetadataFactory.get("id",           "INTEGER", false), // REUSED ← same object
                ColumnMetadataFactory.get("amount",        "DECIMAL", false), // NEW → created
                ColumnMetadataFactory.get("currency",      "VARCHAR", false), // NEW → created
                ColumnMetadataFactory.get("processed_at",  "TIMESTAMP", true) // NEW → created
        });
        System.out.println("Cache size after transactions schema: "
                + ColumnMetadataFactory.getCacheSize()
                + " (id reused — not re-created)");

        System.out.println("\n=== Schema: audit_log ===");
        DatasetSchema auditSchema = new DatasetSchema(new ColumnMetadata[]{
                ColumnMetadataFactory.get("id",           "INTEGER", false),  // REUSED
                ColumnMetadataFactory.get("name",          "VARCHAR", false),  // REUSED
                ColumnMetadataFactory.get("processed_at",  "TIMESTAMP", true), // REUSED
                ColumnMetadataFactory.get("action",        "VARCHAR", false)   // NEW → created
        });
        System.out.println("Cache size after audit_log schema: "
                + ColumnMetadataFactory.getCacheSize()
                + " (id, name, processed_at all reused)");


    }

    private static void runBefore() {
        int totalRows = 1_000_000;
        DataRowBefore[] rows = new DataRowBefore[totalRows];

        for (int i = 0; i < totalRows; i++) {
            // 4 new ColumnMetaBefore objects per row
            // = 4,000,000 identical objects for 1M rows
            rows[i] = new DataRowBefore(
                    new String[]{String.valueOf(i), "Employee_" + i, "Engineering", "95000"}
            );
        }

        System.out.println("Exported " + totalRows + " rows.");
        System.out.println("ColumnMeta objects allocated: " + (totalRows * 4)
                + " (all identical — pure waste)");
        System.out.println("Sample: " + rows[0].describe(1));
    }
}