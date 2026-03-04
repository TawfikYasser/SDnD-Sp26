package solid.university;

import solid.university.after.*;

public class Main {

    public static void main(String[] args) {
        runBefore();
        runAfter();
    }

    static void runBefore() {

        solid.university.before.UniversitySystem sys = new solid.university.before.UniversitySystem();
        sys.registerStudent("REGULAR",       "software design and development", 5000.0);
        sys.registerStudent("SCHOLARSHIP",   "data structures",                 3000.0);
        sys.registerStudent("INTERNATIONAL", "algorithms",                      8000.0);

    }

    static void runAfter() {

        solid.university.after.UniversitySystem sys = new solid.university.after.UniversitySystem(
                new InMemoryRegistrationRepository(),
                new SimpleEmailService(),
                new StudentReportService(),
                new RefundServiceImpl()
        );

        sys.registerStudent(new RegularStudent(),       "software design and development", 5000.0);
        sys.registerStudent(new ScholarshipStudent(),   "data structures",                 3000.0);
        sys.registerStudent(new InternationalStudent(), "algorithms",                      8000.0);

    }
}
