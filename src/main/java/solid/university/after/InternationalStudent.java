package solid.university.after;

public class InternationalStudent implements Student {
    @Override
    public void register(String course) {
        System.out.println("Registering international student to " + course);
    }
}
