package patterns.adapter.dataexportpipeline.after;

public class LegacyCsvReader {
    private String filePath;

    public void openFile(String filePath) {
        this.filePath = filePath;
        System.out.println("[LEGACY CSV] Opening file: " + filePath);
    }

    public String[] readNextRecord() {
        return new String[]{"101", "Alice", "Engineering", "95000"};
    }

    public void closeFile() {
        System.out.println("[LEGACY CSV] File closed.");
    }
}
