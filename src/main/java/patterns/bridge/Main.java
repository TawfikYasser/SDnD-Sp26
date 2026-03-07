package patterns.bridge;

import patterns.bridge.after.*;
import patterns.bridge.before.FullExportToS3;
import patterns.bridge.before.IncrementalExportToFtp;

public class Main {
    public static void main(String[] args) {
        runBefore();
        System.out.println("Bridge");
        runAfter();
    }

    private static void runAfter() {
        new FullExportJob(new LocalStorageTarget()).execute("csvfile");
        new FullExportJob(new S3StorageTarget("bucket")).execute("S3 Bucket");
        new IncrementalExportJob(new S3StorageTarget("bucket"), "day").execute("S3 Bucket");

        new FullExportJob(new AzureBlobStorageTarget("doc1")).execute("Storage in Azure");

    }

    private static void runBefore() {
        new FullExportToS3().execute("orders");
        new IncrementalExportToFtp().execute("transactions", "2025-03-01T00:00:00");
    }
}
