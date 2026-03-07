package patterns.adapter.after;

import java.util.Arrays;
import java.util.List;

public class CsvDataSourceAdapter implements DataSource{

    private final LegacyCsvReader legacyReader;
    private final String filePath;

    public CsvDataSourceAdapter(LegacyCsvReader legacyReader, String filePath) {
        this.legacyReader = legacyReader;
        this.filePath = filePath;
    }

    @Override
    public void connect() {
        legacyReader.openFile(filePath); // translate → legacy
    }

    @Override
    public String[] fetchRows() {
        return new String[]{"101", "Alice", "Engineering", "95000"};
    }

    @Override
    public void disconnect() {
        legacyReader.closeFile(); // translate → legacy
    }
}
