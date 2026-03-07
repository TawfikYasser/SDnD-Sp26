package patterns.decorator.before;

public class MonolithicDataSource {
    private final String filePath;
    private final boolean enableLogging;
    private final boolean enableCompression;
    private final boolean enableEncryption;

    public MonolithicDataSource(String filePath, boolean enableLogging,
                                boolean enableCompression, boolean enableEncryption) {
        this.filePath          = filePath;
        this.enableLogging     = enableLogging;
        this.enableCompression = enableCompression;
        this.enableEncryption  = enableEncryption;
    }

    public void connect() {
        if (enableLogging) System.out.println("[LOG] Connecting to: " + filePath);
        System.out.println("[SOURCE] Connected: " + filePath);
    }

    public String[] fetchRows() {
        if (enableLogging) System.out.println("[LOG] Fetching rows...");
        return new String[]{"1", "Alice", "Engineering", "95000"};
    }

    public void disconnect() {
        if (enableLogging) System.out.println("[LOG] Disconnecting.");
        System.out.println("[SOURCE] Disconnected.");
    }
}
