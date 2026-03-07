package patterns.decorator.after;

import patterns.adapter.after.DataSource;

public class ValidatingDataSource extends DataSourceDecorator{
    private final int minimumRows;

    public ValidatingDataSource(DataSource wrapped, int minimumRows) {
        super(wrapped);
        this.minimumRows = minimumRows;
    }

    @Override
    public String[] fetchRows() {
        String[] rows = wrapped.fetchRows();

        System.out.println("[VALIDATE] Checking row count: got "
                + rows.length + ", expected ≥ " + minimumRows);

        if (rows.length < minimumRows) {
            throw new RuntimeException(
                    "[VALIDATE] ❌ Validation failed: only " + rows.length
                            + " row(s) returned, minimum is " + minimumRows
            );
        }

        System.out.println("[VALIDATE] ✅ Row count check passed.");
        return rows;
    }
}
