package patterns.iterator.after;

public class ValidationEngineV2 {
    public void validate(DataRowIterator iterator, String sourceName) {
        int count = 0;
        while (iterator.hasNext()) {
            String[] row = iterator.next();
            System.out.println("[VALIDATE | " + sourceName + "] "
                    + String.join(", ", row));
            count++;
        }
        System.out.println("[VALIDATE | " + sourceName + "] Total rows: " + count);
    }
}
