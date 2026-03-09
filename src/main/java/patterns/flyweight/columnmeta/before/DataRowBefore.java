package patterns.flyweight.columnmeta.before;

public class DataRowBefore {
    private final ColumnMetaBefore[] schema; // ❌ rebuilt identically on every row
    private final String[] values;

    public DataRowBefore(String[] values) {
        this.schema = new ColumnMetaBefore[]{
                new ColumnMetaBefore("id",        "INTEGER", false),
                new ColumnMetaBefore("name",       "VARCHAR", false),
                new ColumnMetaBefore("department", "VARCHAR", true),
                new ColumnMetaBefore("salary",     "DECIMAL", false)
        };
        this.values = values;
    }

    public String describe(int colIndex) {
        return schema[colIndex].columnName
                + "(" + schema[colIndex].dataType + ") = " + values[colIndex];
    }
}
