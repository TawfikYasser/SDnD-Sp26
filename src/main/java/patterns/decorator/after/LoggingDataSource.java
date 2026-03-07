package patterns.decorator.after;

import patterns.adapter.after.DataSource;

public class LoggingDataSource extends DataSourceDecorator{
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

    @Override public void disconnect() {
        wrapped.disconnect();
        System.out.println("[LOG] Connection closed.");
    }
}
