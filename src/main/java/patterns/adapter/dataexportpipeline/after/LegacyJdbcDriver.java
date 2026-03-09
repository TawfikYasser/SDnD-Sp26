package patterns.adapter.dataexportpipeline.after;

import java.util.Arrays;
import java.util.List;

public class LegacyJdbcDriver {
    private final String jdbcUrl;

    public LegacyJdbcDriver(String jdbcUrl) { this.jdbcUrl = jdbcUrl; }

    public void getConnection() {
        System.out.println("[LEGACY JDBC] Connected to: " + jdbcUrl);
    }

    public String[] executeQuery(String sql) {
        return new String[]{"202", "Sarah", "Engineering", "112000"};
    }

    public void closeConnection() {
        System.out.println("[LEGACY JDBC] Connection closed.");
    }
}
