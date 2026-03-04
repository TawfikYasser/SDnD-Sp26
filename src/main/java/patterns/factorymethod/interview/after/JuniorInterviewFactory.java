package patterns.factorymethod.interview.after;

public class JuniorInterviewFactory implements Interview {
    @Override public Candidate conductInterview() { return new JuniorCandidate(); }
    @Override public Resume    createCV()          { return new JuniorResume(); }
}
