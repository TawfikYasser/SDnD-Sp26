package solid.payment.after;

public class MeezaProcessor implements PaymentProcessor {

    @Override
    public boolean process(double amount) {
        System.out.println("Pay " + amount + " using Meeza!");
        return true;
    }
}
