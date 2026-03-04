package solid.payment.after;

public interface PaymentValidator {
    boolean isValid(Card card);
}
