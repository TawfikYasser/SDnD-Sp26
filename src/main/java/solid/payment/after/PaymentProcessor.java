package solid.payment.after;

public interface PaymentProcessor {
    boolean process(double amount);
}
