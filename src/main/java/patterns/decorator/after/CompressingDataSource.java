package patterns.decorator.after;

import patterns.adapter.after.DataSource;

public class CompressingDataSource extends DataSourceDecorator{
    public CompressingDataSource(DataSource wrapped) { super(wrapped); }

    @Override public String[] fetchRows() {
        String[] rows = wrapped.fetchRows();
        System.out.println("[COMPRESS] Compressing " + rows.length + " row(s) with GZIP...");
        return rows;
    }
}
