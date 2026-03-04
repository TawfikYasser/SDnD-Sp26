package patterns.factorymethod.interview;

import patterns.factorymethod.interview.after.*;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        patterns.factorymethod.interview.before.Candidate c =
                new patterns.factorymethod.interview.before.JuniorCandidate();
        patterns.factorymethod.interview.before.Resume r =
                new patterns.factorymethod.interview.before.JuniorResume();
        System.out.println(c.prepareCandidate() + " | " + r.createCV());

    }

    static void runAfter() {

        Interview[] factories = {
            new JuniorInterviewFactory(),
            new MideLevelInterviewFactory(),
            new SeniorInterviewFactory(),
            new TeamLeaderInterviewFactory()
        };

        for (Interview factory : factories) {
            CandidateSimulator cs = new CandidateSimulator(factory);
            ResumeSimulator    rs = new ResumeSimulator(factory);
            System.out.println(cs.run().prepareCandidate() + " |  " + rs.run().createCV());
        }

    }
}
