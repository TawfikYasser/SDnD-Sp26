package patterns.adapter.after;

import java.util.List;

public class ExportPipeline {
    private final DataSource source;

    public ExportPipeline(DataSource source) {
        this.source = source; // inject any DataSource — CSV, JDBC, API, mock
    }

    public void run() {
        source.connect();
        String[] row = source.fetchRows();
        System.out.println("[PIPELINE] Processing row: " + String.join(", ", row));
        source.disconnect();
    }
}
