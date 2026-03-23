package patterns.templatemethod.before;

import patterns.iterator.after.DataRowIterator;

public class NullCheckJob {
    private final DataRowIterator iterator;
    private final int fieldIndex;

    public NullCheckJob(DataRowIterator iterator, int fieldIndex) {
        this.iterator   = iterator;
        this.fieldIndex = fieldIndex;
    }

    public void run() {
        System.out.println("[NULL CHECK] Starting...");
        String[] issues     = new String[1000];
        int      issueCount = 0;
        int      rowNum     = 0;

        while (iterator.hasNext()) {
            String[] row = iterator.next();
            rowNum++;
            if (row[fieldIndex] == null || row[fieldIndex].isBlank()) {
                issues[issueCount++] =
                        "Row " + rowNum + ": field[" + fieldIndex + "] is null/empty";
            }
        }

        System.out.println("[NULL CHECK] Rows scanned: " + rowNum);
        if (issueCount == 0) {
            System.out.println("[NULL CHECK] ✅ No issues found.");
        } else {
            for (int i = 0; i < issueCount; i++) {
                System.out.println("[NULL CHECK] ❌ " + issues[i]);
            }
        }
    }

}
