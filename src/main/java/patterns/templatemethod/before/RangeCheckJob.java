package patterns.templatemethod.before;

import patterns.iterator.after.DataRowIterator;

public class RangeCheckJob {
    private final DataRowIterator iterator;
    private final int    fieldIndex;
    private final double min, max;

    public RangeCheckJob(DataRowIterator iterator, int fieldIndex, double min, double max) {
        this.iterator   = iterator;
        this.fieldIndex = fieldIndex;
        this.min = min;
        this.max = max;
    }

    public void run() {
        System.out.println("[RANGE CHECK] Starting...");
        String[] issues     = new String[1000];
        int      issueCount = 0;
        int      rowNum     = 0;

        while (iterator.hasNext()) {
            String[] row = iterator.next();
            rowNum++;
            double value = Double.parseDouble(row[fieldIndex]);
            if (value < min || value > max) {
                issues[issueCount++] =
                        "Row " + rowNum + ": value " + value
                                + " out of range [" + min + ", " + max + "]";
            }
        }

        System.out.println("[RANGE CHECK] Rows scanned: " + rowNum);
        if (issueCount == 0) {
            System.out.println("[RANGE CHECK] ✅ No issues found.");
        } else {
            for (int i = 0; i < issueCount; i++) {
                System.out.println("[RANGE CHECK] ❌ " + issues[i]);
            }
        }
    }

}
