package patterns.abstractfactory.interview.after;

public class SeniorInterviewFactory implements Interview {
    @Override public Candidate conductInterview() { return new SeniorCandidate(); }
    @Override public Resume    createCV()          { return new SeniorResume(); }
}
