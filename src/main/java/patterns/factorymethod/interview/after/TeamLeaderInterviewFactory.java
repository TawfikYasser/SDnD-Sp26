package patterns.factorymethod.interview.after;

public class TeamLeaderInterviewFactory implements Interview {
    @Override public Candidate conductInterview() { return new TeamLeaderCandidate(); }
    @Override public Resume    createCV()          { return new TeamLeaderResume(); }
}
