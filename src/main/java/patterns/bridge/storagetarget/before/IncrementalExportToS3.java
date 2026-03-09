package patterns.bridge.storagetarget.before;

public class IncrementalExportToS3 {
    public void execute(String datasetName, String sinceTimestamp) {
        System.out.println("[S3] Incremental export of: " + datasetName
                + " since " + sinceTimestamp);
    }
}
