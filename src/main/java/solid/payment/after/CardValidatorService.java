package solid.payment.after;

public class CardValidatorService implements PaymentValidator {

    @Override
    public boolean isValid(Card card) {
        System.out.println("Card validated using bank API");
        return true;
    }
}
