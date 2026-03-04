package solid.university.after;

public class RegularStudent implements Student {
    @Override
    public void register(String course) {
        System.out.println("Registering regular student to " + course);
    }
}
