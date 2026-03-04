package solid.payment.violation;

public class MeezaProcessor implements PaymentProcessor {

    @Override
    public boolean process(double amount) {
        System.out.println("Pay " + amount + " using Meeza!");
        return true;
    }

    @Override
    public void refund(double amount) {

        throw new UnsupportedOperationException("Meeza does not support refunds!");
    }
}
