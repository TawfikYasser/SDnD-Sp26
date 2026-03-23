package patterns.iterator.before;

public class JdbcResultBuffer {
    private final String[][] rows;
    private int cursor = 0;

    public JdbcResultBuffer(String[][] rows) { this.rows = rows; }

    public boolean  notExhausted()  { return cursor < rows.length; }
    public String[] advanceCursor() { return rows[cursor++]; }
}
