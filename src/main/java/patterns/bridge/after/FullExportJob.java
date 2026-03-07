package patterns.bridge.after;

public class FullExportJob extends ExportJob{
    public FullExportJob(StorageTarget target) {
        super(target);
    }
    @Override
    public void execute(String datasetName) {
        System.out.println("[JOB] Full export started for: " + datasetName);
    }
}
