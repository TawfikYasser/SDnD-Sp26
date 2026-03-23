package patterns.iterator.before;

public class CsvFileSource {
    private final String[] lines;
    private int index = 0;

    public CsvFileSource(String[] lines) {
        this.lines = lines;
    }

    public boolean hasMoreLines(){
        return index < lines.length;
    }

    public String readLine(){
        return lines[index++];
    }
}
