package patterns.singleton.pipeline.before;

import java.util.ArrayList;
import java.util.List;

public class Pipeline {

    private Pipeline() {
        logs = new ArrayList<>();
    }

    List<String> logs;

    private static Pipeline INSTANCE;

    public static Pipeline getInstance() {
        if (INSTANCE == null) {
            synchronized (Pipeline.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Pipeline();
                }
            }
        }
        return INSTANCE;
    }

    public void log(String msg)        { logs.add(msg); }

    public List<String> getLogs()      { return logs; }
}
