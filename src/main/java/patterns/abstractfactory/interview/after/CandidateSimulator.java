package patterns.abstractfactory.interview.after;

public class CandidateSimulator {
    private final Interview factory;

    public CandidateSimulator(Interview factory) { this.factory = factory; }

    public Candidate run() { return factory.conductInterview(); }
}
