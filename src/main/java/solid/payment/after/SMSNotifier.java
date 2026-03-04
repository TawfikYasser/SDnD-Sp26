package solid.payment.after;

public interface SMSNotifier {
    void sendConfirmation(String phone);
}
