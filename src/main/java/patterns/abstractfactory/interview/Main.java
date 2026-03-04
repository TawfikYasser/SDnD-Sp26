package patterns.abstractfactory.interview;

import patterns.abstractfactory.interview.after.*;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        patterns.abstractfactory.interview.before.Candidate c =
                new patterns.abstractfactory.interview.before.JuniorCandidate();
        patterns.abstractfactory.interview.before.Resume r =
                new patterns.abstractfactory.interview.before.JuniorResume();
        System.out.println(c.prepareCandidate() + " | " + r.createCV());

    }

    static void runAfter() {

        Interview[] factories = {
            new JuniorInterviewFactory(),
            new MidLevelInterviewFactory(),
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
