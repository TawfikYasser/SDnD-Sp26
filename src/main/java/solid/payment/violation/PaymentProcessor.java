package solid.payment.violation;

public interface PaymentProcessor {

    boolean process(double amount);

    void refund(double amount);
}
