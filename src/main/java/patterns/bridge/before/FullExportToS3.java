package patterns.bridge.before;

public class FullExportToS3 {
    public void execute(String datasetName) {
        System.out.println("[S3] Full export of dataset: " + datasetName);
    }
}
