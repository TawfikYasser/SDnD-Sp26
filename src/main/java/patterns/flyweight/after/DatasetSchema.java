package patterns.flyweight.after;

public class DatasetSchema {
    private final ColumnMetadata[] columns;

    public DatasetSchema(ColumnMetadata[] columns) {
        this.columns = columns;
    }

    public ColumnMetadata getColumn(int index) { return columns[index]; }
    public int columnCount() { return columns.length; }
}
