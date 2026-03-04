package solid.university.after;

public class ScholarshipStudent implements Student {
    @Override
    public void register(String course) {
        System.out.println("Registering scholarship student to " + course);
    }
}
