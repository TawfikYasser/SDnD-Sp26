package solid.payment.after;

public class ApplicationValidator implements PaymentValidator {

    @Override
    public boolean isValid(Card card) {
        System.out.println("Card validated using application UI");
        return true;
    }
}
