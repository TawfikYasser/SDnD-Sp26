package patterns.bridge.after;

import java.util.List;

public class LocalStorageTarget implements StorageTarget{
    @Override
    public void store() {
        System.out.println("Local Storage");
    }
}
