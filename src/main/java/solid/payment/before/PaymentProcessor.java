package solid.payment.before;

public interface PaymentProcessor {
    boolean process(double amount);
    void refund(double amount);
}
