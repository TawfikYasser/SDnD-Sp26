package patterns.decorator.after;

import patterns.adapter.after.DataSource;

public class RawDataSource implements DataSource {
    private final String filePath;
    public RawDataSource(String filePath) { this.filePath = filePath; }

    @Override public void connect() {
        System.out.println("[SOURCE] Connected to: " + filePath);
    }

    @Override public String[] fetchRows() {
        System.out.println("[SOURCE] Fetching rows from: " + filePath);
        return new String[]{"1", "Alice", "Engineering", "95000"};
    }

    @Override public void disconnect() {
        System.out.println("[SOURCE] Disconnected from: " + filePath);
    }
}
