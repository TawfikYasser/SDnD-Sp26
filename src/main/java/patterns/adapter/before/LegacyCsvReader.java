package patterns.adapter.before;

public class LegacyCsvReader {
    public void openFile(String filePath) {
        System.out.println("[LEGACY CSV] Opening file: " + filePath);
    }

    public String[] readNextRecord() {
        return new String[]{"101", "Alice", "Engineering", "95000"};
    }

    public void closeFile() {
        System.out.println("[LEGACY CSV] File closed.");
    }
}
