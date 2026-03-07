package patterns.adapter.before;

public class ExportPipeline {
    private LegacyCsvReader csvReader = new LegacyCsvReader();

    public void run(String filePath) {
        // Must know openFile/readNextRecord/closeFile — legacy details leak in
        csvReader.openFile(filePath);
        String[] row = csvReader.readNextRecord();
        System.out.println("[PIPELINE] Processing row: " + String.join(", ", row));
        csvReader.closeFile();
        // If we switch to a JDBC source later, this whole class changes
    }
}
