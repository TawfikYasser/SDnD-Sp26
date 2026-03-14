# Bridge: Export Jobs and Storage Targets

## The Problem

In the before version, each combination of export type and storage destination is its own class. `FullExportToS3`, `FullExportToLocal`, `FullExportToFtp`, `IncrementalExportToS3`, `IncrementalExportToLocal`, `IncrementalExportToFtp`. Six classes for two export types and three storage targets. If you add a fourth storage target like Azure Blob Storage, you add two more classes. If you add a third export type, you add three more. The count grows as a product of the two dimensions.

Each of these classes is also essentially a copy of the same idea with different strings. They all do the same thing: run an export of some kind to a destination. There is no reuse and no separation of concerns between the export logic and the destination logic.

## What Changed

The refactored version separates the two dimensions. `ExportJob` is an abstract class that holds a reference to a `StorageTarget`. The concrete export types, `FullExportJob` and `IncrementalExportJob`, extend it and implement `execute()`. The concrete storage targets, `LocalStorageTarget`, `S3StorageTarget`, `FtpStorageTarget`, and `AzureBlobStorageTarget`, each implement the `StorageTarget` interface.

Any export job can be combined with any storage target by passing the target into the job's constructor. Adding Azure Blob Storage takes one new class implementing `StorageTarget` and zero changes to the export jobs. Adding a new export type takes one new class extending `ExportJob` and zero changes to the storage targets.

## Before

```java
// Six separate classes for two types × three targets
public class FullExportToS3 {
    public void execute(String datasetName) {
        System.out.println("[S3] Full export of dataset: " + datasetName);
    }
}

public class IncrementalExportToFtp {
    public void execute(String datasetName, String sinceTimestamp) {
        System.out.println("[FTP] Incremental export of: " + datasetName
                + " since " + sinceTimestamp);
    }
}
// ... and four more nearly identical classes
```

## After

```java
// StorageTarget.java — the implementation side of the bridge
public interface StorageTarget {
    void store();
}

// ExportJob.java — the abstraction side of the bridge
public abstract class ExportJob {
    protected StorageTarget target;  // the bridge reference

    public ExportJob(StorageTarget target) {
        this.target = target;
    }

    public abstract void execute(String datasetName);
}

// FullExportJob.java
public class FullExportJob extends ExportJob {
    public FullExportJob(StorageTarget target) { super(target); }

    @Override
    public void execute(String datasetName) {
        System.out.println("[JOB] Full export started for: " + datasetName);
        target.store();
    }
}

// S3StorageTarget.java
public class S3StorageTarget implements StorageTarget {
    private final String bucket;
    public S3StorageTarget(String bucket) { this.bucket = bucket; }

    @Override
    public void store() { System.out.println("S3 Storage"); }
}

// AzureBlobStorageTarget.java — one new class, no other changes needed
public class AzureBlobStorageTarget implements StorageTarget {
    private final String containerName;
    public AzureBlobStorageTarget(String containerName) { this.containerName = containerName; }

    @Override
    public void store() { System.out.println("AZURE Storage"); }
}
```

And at the call site, any combination works:

```java
new FullExportJob(new LocalStorageTarget()).execute("csvfile");
new FullExportJob(new S3StorageTarget("bucket")).execute("S3 Bucket");
new IncrementalExportJob(new S3StorageTarget("bucket"), "day").execute("S3 Bucket");
new FullExportJob(new AzureBlobStorageTarget("doc1")).execute("Storage in Azure");
```

Two export types and four storage targets, assembled freely, with eight combinations covered by six classes instead of eight.
