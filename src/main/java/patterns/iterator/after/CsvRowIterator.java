package patterns.iterator.after;

public class CsvRowIterator implements DataRowIterator{
    private final String[] lines;
    private int index = 0;

    public CsvRowIterator(String[] lines) { this.lines = lines; }

    @Override
    public boolean hasNext() {
        return index < lines.length;
    }

    @Override
    public String[] next() {
        return lines[index++].split(",");
    }
}
