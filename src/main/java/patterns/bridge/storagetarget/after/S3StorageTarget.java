package patterns.bridge.storagetarget.after;

import java.util.List;

public class S3StorageTarget implements StorageTarget{
    private final String bucket;
    public S3StorageTarget(String bucket) { this.bucket = bucket; }

    @Override
    public void store() {
        System.out.println("S3 Storage");
    }
}
