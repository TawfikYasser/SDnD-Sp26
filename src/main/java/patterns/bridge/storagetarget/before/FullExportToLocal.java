package patterns.bridge.storagetarget.before;

public class FullExportToLocal {
    public void execute(String datasetName) {
        System.out.println("[LOCAL] Full export of dataset: " + datasetName);
    }
}
