package patterns.abstractfactory.interview.after;

public class ResumeSimulator {
    private final Interview factory;

    public ResumeSimulator(Interview factory) { this.factory = factory; }

    public Resume run() { return factory.createCV(); }
}
