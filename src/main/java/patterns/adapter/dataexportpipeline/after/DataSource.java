package patterns.adapter.dataexportpipeline.after;

import java.util.List;

public interface DataSource {
    void connect();
    String[] fetchRows();
    void disconnect();
}
