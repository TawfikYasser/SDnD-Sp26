package patterns.decorator.after;

import patterns.adapter.after.DataSource;

public class EncryptingDataSource extends DataSourceDecorator{
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
