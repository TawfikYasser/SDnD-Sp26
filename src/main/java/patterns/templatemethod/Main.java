package patterns.templatemethod;

import patterns.iterator.after.CsvRowIterator;
import patterns.templatemethod.after.DataQualityJob;
import patterns.templatemethod.after.FormatCheckTemplateJob;
import patterns.templatemethod.after.NullCheckTemplateJob;
import patterns.templatemethod.after.RangeCheckTemplateJob;
import patterns.templatemethod.before.NullCheckJob;
import patterns.templatemethod.before.RangeCheckJob;

public class Main {
    public static void main(String[] args) {
        runBefore();
        System.out.println("Template Method Design Pattern");
        runAfter();
    }

    private static void runAfter() {
        // All checks share the same type — schedulable through a single loop
        DataQualityJob[] jobs = new DataQualityJob[]{
                new NullCheckTemplateJob(
                        new CsvRowIterator(new String[]{
                                "1,Alice,95000",
                                "2,,72000",        // ❌ name is blank
                                "3,Carol,88000"
                        }),
                        1 // check the name field
                ),
                new RangeCheckTemplateJob(
                        new CsvRowIterator(new String[]{
                                "1,Alice,95000",
                                "2,Bob,72000",
                                "3,Carol,-500"     // ❌ salary is negative
                        }),
                        2, 0.0, 200000.0
                ),
                new FormatCheckTemplateJob(
                        new CsvRowIterator(new String[]{
                                "1,alice@corp.com,95000",
                                "2,not-an-email,72000"  // ❌ invalid email
                        }),
                        1, "^[\\w.]+@[\\w.]+\\.[a-z]{2,}$"
                )
        };

        for (DataQualityJob job : jobs) {
            job.run();
            System.out.println();
        }
    }

    private static void runBefore() {
        new NullCheckJob(
                new CsvRowIterator(new String[]{"1,Alice,95000", "2,,72000", "3,Carol,88000"}),
                1 // check the name field
        ).run();

        System.out.println();

        new RangeCheckJob(
                new CsvRowIterator(new String[]{"1,Alice,95000", "2,Bob,72000", "3,Carol,-500"}),
                2, 0.0, 200000.0 // salary must be in [0, 200000]
        ).run();
    }
}
