package patterns.iterator.after;

public class JdbcRowIterator implements DataRowIterator{

    private final String[][] rows;
    private int cursor = 0;

    public JdbcRowIterator(String[][] rows) { this.rows = rows; }

    @Override
    public boolean hasNext() {
        return cursor < rows.length;
    }

    @Override
    public String[] next() {
        return rows[cursor++];
    }
}
