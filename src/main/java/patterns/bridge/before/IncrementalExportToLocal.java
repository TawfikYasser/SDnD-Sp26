package patterns.bridge.before;

public class IncrementalExportToLocal {
    public void execute(String datasetName, String sinceTimestamp) {
        System.out.println("[LOCAL] Incremental export of: " + datasetName
                + " since " + sinceTimestamp);
    }
}
