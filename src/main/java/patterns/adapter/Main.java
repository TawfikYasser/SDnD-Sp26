package patterns.adapter;

import patterns.adapter.after.CsvDataSourceAdapter;
import patterns.adapter.after.DataSource;
import patterns.adapter.after.LegacyCsvReader;
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
    }

    private static void runBefore() {
        ExportPipeline pipeline = new ExportPipeline();
        pipeline.run("/data/employees.csv");
    }
}
