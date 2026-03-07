package patterns.flyweight.after;

public class DataRow {
    private final DatasetSchema schema; // shared flyweight reference
    private final String[] values;      // unique per row

    public DataRow(DatasetSchema schema, String[] values) {
        this.schema = schema;
        this.values = values;
    }

    public void print() {
        for (int i = 0; i < schema.columnCount(); i++) {
            System.out.print("  " + schema.getColumn(i).describe(values[i]));
        }
        System.out.println();
    }
}
