package patterns.bridge.before;

public class IncrementalExportToFtp {
    public void execute(String datasetName, String sinceTimestamp) {
        System.out.println("[FTP] Incremental export of: " + datasetName
                + " since " + sinceTimestamp);
    }
}
