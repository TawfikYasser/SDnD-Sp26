package patterns.adapter.dataexportpipeline.after;

public class JdbcDataSourceAdapter implements DataSource{
    private final LegacyJdbcDriver jdbcDriver;
    private final String query;

    public JdbcDataSourceAdapter(LegacyJdbcDriver jdbcDriver, String query) {
        this.jdbcDriver = jdbcDriver;
        this.query = query;
    }

    @Override
    public void connect() {
        jdbcDriver.getConnection(); // translate → legacy
    }

    @Override
    public String[] fetchRows() {
        return jdbcDriver.executeQuery(query); // translate → legacy
    }

    @Override
    public void disconnect() {
        jdbcDriver.closeConnection(); // translate → legacy
    }
}
