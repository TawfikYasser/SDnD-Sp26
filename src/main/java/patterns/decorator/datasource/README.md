# Decorator: Data Source

## The Problem

The `MonolithicDataSource` in the before version takes boolean flags in its constructor: `enableLogging`, `enableCompression`, `enableEncryption`. Every new feature you want to optionally support has to be added as another constructor parameter. The class then uses `if` checks inside each method to decide whether to execute that feature's logic.

This creates a few problems. First, every combination of features has to be anticipated upfront and expressed through flags. If you want logging and compression but not encryption, you pass `true, true, false`. You still have to think about encryption even to opt out of it. Second, the class keeps growing as features are added. Third, there is no way to compose behaviors at runtime. The combination is fixed at construction time and baked into the conditionals.

What you actually want is to be able to stack behaviors on top of a raw data source in any combination you choose, at the call site, without modifying the underlying class.

## What Changed

The refactored version introduces a `DataSourceDecorator` base class that wraps a `DataSource` and delegates all method calls through to the wrapped object by default. Each concrete decorator like `LoggingDataSource`, `CompressingDataSource`, `EncryptingDataSource`, and `ValidatingDataSource` extends this base and overrides only the methods it cares about, adding its behavior before or after the delegate call.

Because every decorator also implements `DataSource`, you can wrap decorators inside other decorators. The call travels down the chain through each layer. You can build any combination by nesting constructors at the call site, and you can change the combination at runtime without touching the decorator classes themselves.

## Before

```java
public class MonolithicDataSource {
    private final String filePath;
    private final boolean enableLogging;
    private final boolean enableCompression;
    private final boolean enableEncryption;

    public MonolithicDataSource(String filePath, boolean enableLogging,
                                boolean enableCompression, boolean enableEncryption) { ... }

    public void connect() {
        if (enableLogging) System.out.println("[LOG] Connecting to: " + filePath);
        System.out.println("[SOURCE] Connected: " + filePath);
    }

    public String[] fetchRows() {
        if (enableLogging) System.out.println("[LOG] Fetching rows...");
        return new String[]{"1", "Alice", "Engineering", "95000"};
    }
    // adding a new feature = another boolean flag + more if branches
}
```

## After

```java
// DataSourceDecorator.java — delegates everything by default
public class DataSourceDecorator implements DataSource {
    protected final DataSource wrapped;

    public DataSourceDecorator(DataSource wrapped) { this.wrapped = wrapped; }

    @Override public void connect()           { wrapped.connect(); }
    @Override public String[] fetchRows()     { return wrapped.fetchRows(); }
    @Override public void disconnect()        { wrapped.disconnect(); }
}

// LoggingDataSource.java — adds logging around the delegate
public class LoggingDataSource extends DataSourceDecorator {
    public LoggingDataSource(DataSource wrapped) { super(wrapped); }

    @Override public void connect() {
        System.out.println("[LOG] Initiating connection...");
        wrapped.connect();
    }

    @Override public String[] fetchRows() {
        System.out.println("[LOG] Fetching rows...");
        String[] rows = wrapped.fetchRows();
        System.out.println("[LOG] Fetched " + rows.length + " row(s).");
        return rows;
    }
}

// EncryptingDataSource.java — encrypts rows after fetching
public class EncryptingDataSource extends DataSourceDecorator {
    private final String keyId;

    public EncryptingDataSource(DataSource wrapped, String keyId) {
        super(wrapped);
        this.keyId = keyId;
    }

    @Override public String[] fetchRows() {
        String[] rows = wrapped.fetchRows();
        System.out.println("[ENCRYPT] Encrypting rows with key: " + keyId);
        return rows;
    }
}
```

Composing behaviors at the call site:

```java
DataSource base = new RawDataSource("/data/employees.csv");

// Logged + Compressed
DataSource forFtp = new LoggingDataSource(new CompressingDataSource(base));

// Logged + Encrypted + Compressed for sensitive S3 upload
DataSource secure = new LoggingDataSource(
        new CompressingDataSource(
                new EncryptingDataSource(base, "arn:aws:kms:key-42")));

// With a validation guard
DataSource guarded = new LoggingDataSource(
        new ValidatingDataSource(
                new CompressingDataSource(base), 1));
```

Each decorator does one thing. You choose which ones to include and in what order at the call site, without changing any existing code.
