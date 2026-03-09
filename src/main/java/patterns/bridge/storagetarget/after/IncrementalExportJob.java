package patterns.bridge.storagetarget.after;

public class IncrementalExportJob extends ExportJob{
    private final String sinceTimestamp;

    public IncrementalExportJob(StorageTarget target, String sinceTimestamp) {
        super(target);
        this.sinceTimestamp = sinceTimestamp;
    }

    @Override
    public void execute(String datasetName) {
        System.out.println("[JOB] Incremental export for: "
                + datasetName + " (since " + sinceTimestamp + ")");
    }
}
