package patterns.decorator.datasource.after;

import patterns.adapter.dataexportpipeline.after.DataSource;

public class DataSourceDecorator implements DataSource {
    protected final DataSource wrapped;

    public DataSourceDecorator(DataSource wrapped) { this.wrapped = wrapped; }

    @Override public void connect()             { wrapped.connect(); }
    @Override public String[] fetchRows() { return wrapped.fetchRows(); }
    @Override public void disconnect()          { wrapped.disconnect(); }
}
