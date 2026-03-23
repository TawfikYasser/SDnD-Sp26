package patterns.iterator.before;

public class ValidationEngine {
    public void validateCsv(CsvFileSource csv) {
        while (csv.hasMoreLines()) {
            String[] row = csv.readLine().split(",");
            System.out.println("[CSV VALIDATE] " + String.join(", ", row));
        }
    }

    public void validateJdbc(JdbcResultBuffer jdbc) {
        while (jdbc.notExhausted()) {
            String[] row = jdbc.advanceCursor();
            System.out.println("[JDBC VALIDATE] " + String.join(", ", row));
        }
    }

    public void validateApi(ApiPagedResponse api) {
        while (api.hasNextPage()) {
            String[] row = api.fetchPage();
            System.out.println("[API VALIDATE] " + String.join(", ", row));
        }
    }
}
