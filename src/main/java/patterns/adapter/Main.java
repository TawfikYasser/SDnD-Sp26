package patterns.adapter;

import patterns.adapter.after.*;
import patterns.adapter.before.ExportPipeline;

public class Main {
    public static void main(String[] args) {
        runBefore();
        System.out.println("Adapter");
        runAfter();
    }

    private static void runAfter() {
        DataSource source = new CsvDataSourceAdapter(
                new LegacyCsvReader(), "/data/employees.csv"
        );
        new patterns.adapter.after.ExportPipeline(source).run();

        DataSource jdbcSource = new JdbcDataSourceAdapter(
                new LegacyJdbcDriver("jdbc:oracle:thin:@prod-db:1521:ORCL"),
                "SELECT id, name, department, salary FROM employees"
        );
        new patterns.adapter.after.ExportPipeline(jdbcSource).run();
    }

    private static void runBefore() {
        ExportPipeline pipeline = new ExportPipeline();
        pipeline.run("/data/employees.csv");
    }
}
