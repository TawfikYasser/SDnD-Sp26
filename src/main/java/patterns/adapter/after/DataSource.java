package patterns.adapter.after;

import java.util.List;

public interface DataSource {
    void connect();
    String[] fetchRows();
    void disconnect();
}
