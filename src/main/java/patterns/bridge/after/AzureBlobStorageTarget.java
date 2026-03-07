package patterns.bridge.after;

public class AzureBlobStorageTarget implements StorageTarget{

    private final String containerName;

    public AzureBlobStorageTarget(String containerName) {
        this.containerName  = containerName;
    }

    @Override
    public void store() {
        System.out.println("AZURE Storage");
    }
}
