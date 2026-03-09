package patterns.decorator.datasource.after;

import patterns.adapter.dataexportpipeline.after.DataSource;

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
