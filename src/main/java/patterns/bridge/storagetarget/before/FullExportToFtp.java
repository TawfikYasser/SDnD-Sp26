package patterns.bridge.storagetarget.before;

public class FullExportToFtp {
    public void execute(String datasetName) {
        System.out.println("[FTP] Full export of dataset: " + datasetName);
    }
}
