package patterns.templatemethod.after;

import patterns.iterator.after.DataRowIterator;

public abstract class DataQualityJob {
    private final String checkName;
    protected final DataRowIterator iterator;

    public DataQualityJob(String checkName, DataRowIterator iterator) {
        this.checkName = checkName;
        this.iterator  = iterator;
    }

    // ── Template Method — final: subclasses cannot reorder or skip steps ───────
    public final void run() {
        System.out.println("[" + checkName + "] Starting...");
        String[] issues     = new String[1000];
        int      issueCount = 0;
        int      rowNum     = 0;

        while (iterator.hasNext()) {
            String[] row = iterator.next();
            rowNum++;
            String issue = validate(row, rowNum); // ← hook: each subclass fills this in
            if (issue != null) issues[issueCount++] = issue;
        }

        printReport(rowNum, issues, issueCount); // ── shared method
    }

    // ── Hook — subclass defines what "invalid" means for its specific rule ─────
    protected abstract String validate(String[] row, int rowNum);

    // ─── Shared report printer ────────────────────────────────────────────────
    private void printReport(int rowNum, String[] issues, int issueCount) {
        System.out.println("[" + checkName + "] Rows scanned: " + rowNum);
        if (issueCount == 0) {
            System.out.println("[" + checkName + "] ✅ No issues found.");
        } else {
            for (int i = 0; i < issueCount; i++) {
                System.out.println("[" + checkName + "] ❌ " + issues[i]);
            }
        }
    }
}
