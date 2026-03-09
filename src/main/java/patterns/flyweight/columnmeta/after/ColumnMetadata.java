package patterns.flyweight.columnmeta.after;

public class ColumnMetadata {
    private final String columnName;
    private final String dataType;
    private final boolean nullable;

    public ColumnMetadata(String columnName, String dataType, boolean nullable) {
        this.columnName = columnName;
        this.dataType   = dataType;
        this.nullable   = nullable;
        System.out.println("  [FLYWEIGHT] ColumnMetadata created: "
                + columnName + " (" + dataType + ")");
    }

    public String describe(String value) {
        return columnName + "(" + dataType
                + (nullable ? ", nullable" : "") + ") = " + value;
    }

    public String getColumnName() { return columnName; }
}
