package solid.university.after;

public class SimpleEmailService implements EmailService {
    @Override
    public void sendEmail(String message) {
        System.out.println("Email sent: " + message);
    }
}
