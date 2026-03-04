package patterns.factorymethod.interview.after;

public class MidLevelInterviewFactory implements Interview {
    @Override public Candidate conductInterview() { return new MidLevelCandidate(); }
    @Override public Resume    createCV()          { return new MidLevelResume(); }
}
