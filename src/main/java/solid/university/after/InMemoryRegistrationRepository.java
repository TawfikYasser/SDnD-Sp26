package solid.university.after;

public class InMemoryRegistrationRepository implements RegistrationRepository {
    @Override
    public void save(String course, double fees) {
        System.out.println("Saving registration (simulated): " + course + " / " + fees);
    }
}
