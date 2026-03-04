package patterns.singleton.pipeline.after;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PipelineLogger {

    private final List<String> logs;

    private PipelineLogger() {
        logs = Collections.synchronizedList(new ArrayList<>());
        System.out.println("PipelineLogger initialised (Holder idiom)");
    }

    private static class Holder {
        private static final PipelineLogger INSTANCE = new PipelineLogger();
    }

    public static PipelineLogger getInstance() {
        return Holder.INSTANCE;
    }

    public void log(String msg) { logs.add(msg); }

    public List<String> getLogs() { return logs; }
}
