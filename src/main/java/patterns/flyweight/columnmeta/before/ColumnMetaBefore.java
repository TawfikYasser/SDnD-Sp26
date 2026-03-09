package patterns.flyweight.columnmeta.before;

public class ColumnMetaBefore {
    String columnName;
    String dataType;
    boolean nullable;

    public ColumnMetaBefore(String columnName, String dataType, boolean nullable) {
        this.columnName = columnName;
        this.dataType = dataType;
        this.nullable = nullable;
    }
}
