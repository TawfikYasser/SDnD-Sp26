package patterns.decorator.datasource;

import patterns.adapter.dataexportpipeline.after.DataSource;
import patterns.decorator.datasource.after.CompressingDataSource;
import patterns.decorator.datasource.after.EncryptingDataSource;
import patterns.decorator.datasource.after.LoggingDataSource;
import patterns.decorator.datasource.after.RawDataSource;
import patterns.decorator.datasource.after.ValidatingDataSource;
import patterns.decorator.datasource.before.MonolithicDataSource;

public class Main {
    public static void main(String[] args) {
        runBefore();
        System.out.println("Decorator");
        runAfter();
    }

    private static void runAfter() {
        DataSource base = new RawDataSource("/data/employees.csv");

        System.out.println("=== Raw source (no extras) ===");
        base.connect();
        base.fetchRows();
        base.disconnect();

        System.out.println("\n=== Logged + Compressed (FTP upload) ===");
        new LoggingDataSource(new CompressingDataSource(base))
                .connect();

        System.out.println("\n=== Logged + Encrypted + Compressed (S3 sensitive) ===");
        DataSource secure = new LoggingDataSource(
                new CompressingDataSource(
                        new EncryptingDataSource(base, "arn:aws:kms:key-42")));
        secure.connect();
        secure.fetchRows();
        secure.disconnect();


        System.out.println("\n=== Logged + Validated + Compressed (export with guard) ===");
        DataSource guardedSource = new LoggingDataSource(
                new ValidatingDataSource(
                        new CompressingDataSource(base), 1));
        guardedSource.connect();
        guardedSource.fetchRows();
        guardedSource.disconnect();

        System.out.println("\n=== Validation failure case (min = 100 rows) ===");
        DataSource strictSource = new LoggingDataSource(
                new ValidatingDataSource(base, 100));
        strictSource.connect();
        try {
            strictSource.fetchRows();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        strictSource.disconnect();

    }

    private static void runBefore() {
        // Want logging + compression only? Still pass encryption=false
        MonolithicDataSource s1 = new MonolithicDataSource(
                "/data/orders.csv", true, true, false
        );
        s1.connect();
        s1.fetchRows();
        s1.disconnect();
    }
}
