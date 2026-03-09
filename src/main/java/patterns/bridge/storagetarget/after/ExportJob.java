package patterns.bridge.storagetarget.after;

public abstract class ExportJob {
    protected StorageTarget target; // ← the bridge

    public ExportJob(StorageTarget target) {
        this.target = target;
    }

    public abstract void execute(String datasetName);
}
