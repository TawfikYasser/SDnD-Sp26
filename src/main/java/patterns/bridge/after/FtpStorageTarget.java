package patterns.bridge.after;

import java.util.List;

public class FtpStorageTarget implements StorageTarget{
    private final String host;
    public FtpStorageTarget(String host) { this.host = host; }

    @Override
    public void store() {
        System.out.println("FTP Storage");
    }
}
