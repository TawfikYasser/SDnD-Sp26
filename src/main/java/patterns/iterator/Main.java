package patterns.iterator;

import patterns.iterator.after.ApiPageIterator;
import patterns.iterator.after.CsvRowIterator;
import patterns.iterator.after.JdbcRowIterator;
import patterns.iterator.after.ValidationEngineV2;
import patterns.iterator.before.ValidationEngine;
import patterns.iterator.before.ApiPagedResponse;
import patterns.iterator.before.CsvFileSource;
import patterns.iterator.before.JdbcResultBuffer;

public class Main {
    public static void main(String[] args) {
        runBefore();
        System.out.println("iterator");
        runAfter();
    }

    private static void runAfter() {
        ValidationEngineV2 engine = new ValidationEngineV2();

        engine.validate(
                new CsvRowIterator(new String[]{"1,Alice,95000", "2,Bob,72000"}),
                "CSV"
        );

        engine.validate(
                new JdbcRowIterator(new String[][]{
                        {"3", "Carol", "98000"},
                        {"4", "Dave",  "65000"}
                }),
                "JDBC"
        );

        engine.validate(
                new ApiPageIterator(new String[][]{
                        {"5", "Eve",   "102000"},
                        {"6", "Frank", "88000"}
                }),
                "API"
        );
    }

    private static void runBefore() {
        ValidationEngine engine = new ValidationEngine();

        engine.validateCsv(
                new CsvFileSource(new String[]{"1,Alice,95000", "2,Bob,72000"})
        );

        engine.validateJdbc(
                new JdbcResultBuffer(new String[][]{
                        {"3", "Carol", "98000"}
                })
        );

        engine.validateApi(
                new ApiPagedResponse(new String[][]{
                        {"4", "Dave", "65000"}
                })
        );
    }
}
