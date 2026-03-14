# Adapter: Data Export Pipeline

## The Problem

The `ExportPipeline` in the before version holds a direct reference to `LegacyCsvReader` and calls `openFile`, `readNextRecord`, and `closeFile` on it. The pipeline is completely tied to the CSV reader's specific method names and signature.

The immediate problem is that these are legacy API names. They don't match whatever interface a modern pipeline would expect. More importantly, if you ever need to switch the data source from a CSV file to a JDBC database connection, you have to rewrite the pipeline class. The pipeline and the data source are fused together, so you cannot swap one without touching the other.

There is also no abstraction the pipeline can work through. If you want to test the pipeline in isolation, you cannot inject a mock or a fake data source because there is no interface to stand behind.

## What Changed

The refactored version introduces a `DataSource` interface with three methods: `connect()`, `fetchRows()`, and `disconnect()`. `CsvDataSourceAdapter` wraps `LegacyCsvReader` and translates those three calls into the legacy reader's specific API. `JdbcDataSourceAdapter` does the same for `LegacyJdbcDriver`.

The pipeline now takes a `DataSource` in its constructor. It calls `connect()`, `fetchRows()`, and `disconnect()`, and has no idea whether the underlying source is a CSV file, a database, an API, or a mock. Swapping sources at the call site requires no changes to `ExportPipeline` at all.

The legacy code is untouched. The adapters act as a translation layer between the interface the pipeline expects and the interface the legacy code provides.

## Before

```java
public class ExportPipeline {
    private LegacyCsvReader csvReader = new LegacyCsvReader();

    public void run(String filePath) {
        // Pipeline knows all the legacy details
        csvReader.openFile(filePath);
        String[] row = csvReader.readNextRecord();
        System.out.println("[PIPELINE] Processing row: " + String.join(", ", row));
        csvReader.closeFile();
        // Switching to JDBC requires rewriting this class
    }
}
```

## After

```java
// DataSource.java — the target interface
public interface DataSource {
    void connect();
    String[] fetchRows();
    void disconnect();
}

// CsvDataSourceAdapter.java — wraps the legacy CSV reader
public class CsvDataSourceAdapter implements DataSource {
    private final LegacyCsvReader legacyReader;
    private final String filePath;

    public CsvDataSourceAdapter(LegacyCsvReader legacyReader, String filePath) {
        this.legacyReader = legacyReader;
        this.filePath = filePath;
    }

    @Override public void connect()        { legacyReader.openFile(filePath); }
    @Override public String[] fetchRows()  { return legacyReader.readNextRecord(); }
    @Override public void disconnect()     { legacyReader.closeFile(); }
}

// ExportPipeline.java — only knows DataSource
public class ExportPipeline {
    private final DataSource source;

    public ExportPipeline(DataSource source) {
        this.source = source;
    }

    public void run() {
        source.connect();
        String[] row = source.fetchRows();
        System.out.println("[PIPELINE] Processing row: " + String.join(", ", row));
        source.disconnect();
    }
}
```

Switching between a CSV file and a JDBC connection at the call site:

```java
DataSource csv = new CsvDataSourceAdapter(new LegacyCsvReader(), "/data/employees.csv");
new ExportPipeline(csv).run();

DataSource jdbc = new JdbcDataSourceAdapter(
    new LegacyJdbcDriver("jdbc:oracle:thin:@prod-db:1521:ORCL"),
    "SELECT id, name, department, salary FROM employees"
);
new ExportPipeline(jdbc).run();
```

`ExportPipeline` does not change at all between the two cases.
