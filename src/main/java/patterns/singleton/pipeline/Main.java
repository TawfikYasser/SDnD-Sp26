package patterns.singleton.pipeline;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        patterns.singleton.pipeline.before.Pipeline p1 =
                patterns.singleton.pipeline.before.Pipeline.getInstance();
        patterns.singleton.pipeline.before.Pipeline p2 =
                patterns.singleton.pipeline.before.Pipeline.getInstance();

        p1.log("pipeline started");
        p2.log("reading from kafka topic");

        System.out.println("p1 == p2: " + (p1 == p2));
        System.out.println("logs shared: " + (p1.getLogs().size() == 2));
        p1.getLogs().forEach(e -> System.out.println("> " + e));

        System.out.println("INSTANCE is not volatile, another thread could see a");
    }

    static void runAfter() {

        patterns.singleton.pipeline.after.PipelineLogger l1 =
                patterns.singleton.pipeline.after.PipelineLogger.getInstance();
        patterns.singleton.pipeline.after.PipelineLogger l2 =
                patterns.singleton.pipeline.after.PipelineLogger.getInstance();

        l1.log("pipeline started");
        l2.log("reading from kafka topic");

        System.out.println("l1 == l2: " + (l1 == l2));
        System.out.println("log count is 2: " + (l1.getLogs().size() == 2));
        l1.getLogs().forEach(e -> System.out.println("> " + e));
    }
}
